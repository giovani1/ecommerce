package com.ensa.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.OrderForm;
import com.ensa.models.Cart;
import com.ensa.models.Client;
import com.ensa.models.OrderProducts;
import com.ensa.models.Orders;
import com.ensa.models.Product;
import com.ensa.service.AdressService;
import com.ensa.service.CartService;
import com.ensa.service.OrderService;
import com.ensa.service.ProductService;
import com.ensa.util.Cons;
import com.ensa.util.Name;

/**
 * Servlet implementation class Order
 */
@WebServlet(value={"/order","/orders","/order/add","/order/confirm","/order/comment","/order/add/2","/order/add/1"})
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_ORDERS = "/WEB-INF/views/orders.jsp";
	//private static final String VUE_ORDERS_SELLER = "/WEB-INF/views/ordersSeller.jsp";
	private static final String VUE_ORDER = "/WEB-INF/views/order.jsp";
	private static final String ATT_ORDER = "order";
	private static final String ATT_ORDERS = "orders";

	//private static final String ATT_PRODUCTS = "products";

	private static final String VUE_ADD_ORDER = "/WEB-INF/views/client/add_order.jsp";

	private static final String ATT_CART = "cart";

	
	
    @EJB
	OrderService os;
    @EJB
	CartService cs;
    @EJB
	ProductService ps;
    @EJB
    AdressService as;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String accountType=(String) session.getAttribute("accountType");
		if(accountType=="client"){
			Client account=(Client) session.getAttribute("account");
			if(request.getServletPath().equals("/orders")){
				request.setAttribute(ATT_ORDERS, os.findByClient(account.getId()));
				this.getServletContext().getRequestDispatcher(VUE_ORDERS).forward(request,response);
			}else if(request.getServletPath().equals("/order")){
				OrderForm form=new OrderForm(cs,os,as);
				Orders order=form.getOrder(request);
				if(form.getResult()=="true"){
					request.setAttribute(Cons.ATT_ORDER, order);
					this.getServletContext().getRequestDispatcher(VUE_ORDER).forward(request,response);	
				}
			}else if(request.getServletPath().equals("/order/confirm")){
				OrderForm form=new OrderForm(cs,os,as);
				form.confirmOrder(request);
				response.sendRedirect(this.getServletContext().getContextPath()+"/orders");
			}else
			response.sendRedirect(this.getServletContext().getContextPath()+"/orders");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		if(session.getAttribute(Name.ACCOUNT) != null){
			if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
				Client client=(Client)request.getSession().getAttribute(Name.ACCOUNT);
				if(request.getServletPath().equals("/order/add/1")){
					request.setAttribute(ATT_CART, cs.findByClient(client.getId()));
					session.setAttribute(Name.CLIENT, client);
					this.getServletContext().getRequestDispatcher(VUE_ADD_ORDER).forward(request,response);
				}
				else if(request.getServletPath().equals("/order/add/2")){
					OrderForm form=new OrderForm(cs,os,as);
					Orders order=form.addOrder(request,client);
					
					
					if(form.getResult()=="true"){
						List<OrderProducts> s =order.getOrder_products();
						for(int i=0;i<s.size();i++){
							Product p=s.get(i).getProduct();
							p.setQuantity(p.getQuantity()-s.get(i).getProduct_quantity());
							ps.update(p);
						}
						order=os.add(order);
						List<Cart> carts=cs.findByClient(client.getId());
						for(int i=0;i<carts.size();i++){
							cs.removeCart(carts.get(i));
						}	
						response.sendRedirect(this.getServletContext().getContextPath()+"/orders");
					}
					
				}
				else if(request.getServletPath().equals("/order/comment")){
					OrderForm form=new OrderForm(cs,os,as);
					Orders order=form.updateComment(request);
					if(form.getResult()=="true"){
						os.update(order);
					}
				}else
				response.sendRedirect(this.getServletContext().getContextPath()+"/orders");
			}
		}
	}

}