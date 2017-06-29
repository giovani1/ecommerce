package com.ensa.controllers;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.LoginForm;
import com.ensa.models.Client;
import com.ensa.models.Seller;
import com.ensa.service.CartService;
import com.ensa.service.ClientService;
import com.ensa.service.SellerService;
import com.ensa.util.Name;

/**
 * Servlet implementation class LoginClient
 */
@WebServlet(value={"/login/seller","/login/client"})
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    
    @EJB
    ClientService cs;
    @EJB
    SellerService ss;
    @EJB
    CartService cc;
    
    public LoginController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/login/client")){
			loginClientGet(request,response);
		}else if(request.getServletPath().equals("/login/seller")){
			loginSellerGet(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/login/client")){
			loginClientPost(request,response);
		}else if(request.getServletPath().equals("/login/seller")){
			loginSellerPost(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}

	
	private void loginClientGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute(Name.ACCOUNT) == null){
			this.getServletContext().getRequestDispatcher(Name.LOGIN_CLIENT_PAGE).forward(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void loginSellerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute(Name.ACCOUNT) == null){
			this.getServletContext().getRequestDispatcher(Name.LOGIN_SELLER_PAGE).forward(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void loginClientPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LoginForm form = new LoginForm();
		Client client = form.loginClient(request);
		
		if(form.getResult().equals("true")){
			
			Client clientFromCS = (Client)cs.getClient(client.getEmail(), client.getPassword());
			
			if(clientFromCS != null && clientFromCS.getStatus()){
				//the seller exist in the database
				clientFromCS.getInfo().setLastLoginDate(new Date());
				clientFromCS.getInfo().setNumbers_logons(clientFromCS.getInfo().getNumbers_logons() + 1 );
				clientFromCS = cs.update(clientFromCS);
				
				HttpSession session = request.getSession();
				
				session.setAttribute(Name.ACCOUNT,clientFromCS);
				session.setAttribute(Name.ACCOUNT_TYPE, "client");
				session.setAttribute(Name.SIZE_OF_CART,cc.findByClient(((Client)request.getSession().getAttribute(Name.ACCOUNT)).getId()).size());
			
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
				
			}else{
				//a error message must be set into the form object
				this.getServletContext().getRequestDispatcher(Name.LOGIN_CLIENT_PAGE).forward(request,response);
			}
		
		}else{
			
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.CLIENT, client);
			this.getServletContext().getRequestDispatcher(Name.LOGIN_CLIENT_PAGE).forward(request,response);
			
		}
	}
	
	private void loginSellerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//using the same validator form as client logint form because it will handle the same fields
		LoginForm form = new LoginForm();
		Client client = form.loginClient(request);
		
		if(form.getResult().equals("true")){
			System.out.println(client);
			Seller sellerFromSS = ss.getSeller(client.getEmail(), client.getPassword());
			if(sellerFromSS != null && sellerFromSS.getStatus()){
				//the seller exit in the database
				sellerFromSS.getInfo().setLastLoginDate(new Date());
			
				sellerFromSS = ss.update(sellerFromSS);
				
				HttpSession session = request.getSession();
				
				session.setAttribute(Name.ACCOUNT,sellerFromSS);
				session.setAttribute(Name.ACCOUNT_TYPE, "seller");
				System.out.println("--"+session.getAttribute(Name.ACCOUNT_TYPE));
				
				request.getSession().setAttribute(Name.ACCOUNT,sellerFromSS);
				
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			}
			else{
				//a error message must be set into the form object
				this.getServletContext().getRequestDispatcher(Name.LOGIN_SELLER_PAGE).forward(request,response);
				
			}
			
		}else{
			
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.CLIENT, client);
			this.getServletContext().getRequestDispatcher(Name.LOGIN_SELLER_PAGE).forward(request,response);
			
		}
	}
}
