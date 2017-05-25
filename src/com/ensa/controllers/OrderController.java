package com.ensa.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.OrderForm;
import com.ensa.models.Client;
import com.ensa.models.Orders;
import com.ensa.service.CartService;
import com.ensa.service.OrderService;
import com.ensa.util.Cons;

/**
 * Servlet implementation class Order
 */
@WebServlet(value={"/order","/orders","/order/add"})
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_ORDERS = "/WEB-INF/views/orders.jsp";
	private static final String VUE_ORDER = "/WEB-INF/views/order.jsp";
	private static final String ATT_ORDER = "order";
	
    @EJB
	OrderService os;
    @EJB
	CartService cs;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/orders")){
			request.setAttribute(ATT_ORDER, os.findAll());
			this.getServletContext().getRequestDispatcher(VUE_ORDERS).forward(request,response);
		}else if(request.getServletPath().equals("/order")){
			OrderForm form=new OrderForm(cs,os);
			Orders order=form.getOrder(request);
			if(form.getResult()=="true"){
				request.setAttribute(Cons.ATT_ORDER, order);
				this.getServletContext().getRequestDispatcher(VUE_ORDER).forward(request,response);	
			}
			else{
				response.sendRedirect(this.getServletContext().getContextPath()+"/orders");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		Client client=(Client) session.getAttribute("account");
		if(request.getServletPath().equals("/order/add")){
			OrderForm form=new OrderForm(cs,os);
			Orders order=form.addOrder(request,client);
			if(form.getResult()=="true"){
				order=os.add(order);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/orders");
		}
		
	}

}
