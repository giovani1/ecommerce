package com.ensa.controllers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.CartForm;
import com.ensa.models.Cart;
import com.ensa.models.Client;
import com.ensa.service.CartService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;

@WebServlet(value={"/cart","/cart/add","/cart/update","/cart/delete"})
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_CART = "/WEB-INF/views/client/cart.jsp";
	private static final String ATT_CART = "cart";
    
	@EJB
	CartService cs;
	@EJB
	ProductService ps;
	@EJB
	Product_attributesService pas;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/cart")){
			request.setAttribute(ATT_CART, cs.findAll());
			this.getServletContext().getRequestDispatcher( VUE_CART ).forward( request, response );
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		if(request.getServletPath().equals("/cart/add")){
			Client client=(Client)session.getAttribute("account");
			CartForm form=new CartForm(cs,ps,pas);
			Cart cart=form.addCart(request, client);
			if(form.getResult()=="true"){
				cs.addCart(cart);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/cart");
		}else if(request.getServletPath().equals("/cart/update")){
			CartForm form=new CartForm(cs,ps,pas);
			Cart cart=form.updateCart(request);
			if(form.getResult()=="true"){
				cs.updateCart(cart);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/cart");
		}else if(request.getServletPath().equals("/cart/delete")){
			CartForm form=new CartForm(cs,ps,pas);
			Cart cart=form.deleteCart(request);
			if(form.getResult()=="true"){
				cs.removeCart(cart);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/cart");
		}
	}

}
