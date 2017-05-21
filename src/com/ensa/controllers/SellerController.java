package com.ensa.controllers;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.SellerForm;
import com.ensa.models.Seller;
import com.ensa.service.SellerService;
import com.ensa.util.Name;

/**
 * Servlet implementation class AddSeller
 */
@WebServlet(value={"/seller","/seller/add","/seller/update"})
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    @EJB
    SellerService ss;
    
    
    public SellerController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/seller/add")){
			addSellerGet(request,response);
		}else if(request.getServletPath().equals("/seller/update")){
			updateSellerGet(request,response);
		}else if(request.getServletPath().equals("/seller")){
			sellerProfil(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/seller/add")){
			addSellerPost(request,response);
		}else if(request.getServletPath().equals("/seller/update")){
			updateSellerPost(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void sellerProfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute(Name.ACCOUNT) != null && ((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("seller")){
			Seller seller = (Seller)session.getAttribute(Name.ACCOUNT);
			request.setAttribute(Name.SELLER, seller);
			this.getServletContext().getRequestDispatcher(Name.PROFIL_SELLER_PAGE).forward(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void addSellerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(Name.ACCOUNT) == null){
			this.getServletContext().getRequestDispatcher(Name.ADD_SELLER_FORM).forward(request, response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void updateSellerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//check if the client is logged 
		if(session.getAttribute(Name.ACCOUNT) != null && ((String)session.getAttribute(Name.ACCOUNT)).equals("seller") ){
			
			Seller seller = (Seller)session.getAttribute(Name.ACCOUNT);
			
			//fill old information in a new client object
			Seller sellerUpdate = new Seller();
			sellerUpdate.setEmail(seller.getEmail());
			sellerUpdate.setPerson(seller.getPerson());;
			sellerUpdate.setPassword(seller.getPassword());
			sellerUpdate.setPhone(seller.getPhone());
			sellerUpdate.setInfo(seller.getInfo());
			sellerUpdate.setSeller_name(seller.getSeller_name());
			
			//set the information in the request 
			request.setAttribute(Name.SELLER, sellerUpdate);
			this.getServletContext().getRequestDispatcher(Name.UPDATE_SELLER_FORM).forward(request,response);
		}else{
			
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			
		}
	}

	
	private void addSellerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SellerForm form = new SellerForm();
		Seller seller = form.addSeller(request);
		
		
		if(form.getResult().equals("true")){
			//set registration information
			seller.getInfo().setLastLoginDate(new Date());
			seller.getInfo().setLastModifiedDate(new Date());
			seller.getInfo().setRegistrationDate(new Date());
			seller.getInfo().setUrl_clicked(0);
			seller.setStatus(true);
			try{
				
				if(ss.getSeller(seller.getEmail()) == null){
					seller = ss.add(seller);
					System.out.println("||the seller added");
					request.getSession().setAttribute(Name.ACCOUNT, seller);
					request.getSession().setAttribute(Name.ACCOUNT_TYPE, "seller");
				

					response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
					
				}else{
					
					request.setAttribute(Name.FORM, form);
					request.setAttribute(Name.SELLER, seller);
					request.setAttribute(Name.USED_EMAIL,"l'email que vous avez entrez est deja utilise , veuillez choisir une autre adresse email");
					this.getServletContext().getRequestDispatcher(Name.ADD_SELLER_FORM).forward(request,response);
				
				}
				
			}catch(EJBException e){
				
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			
			}
			
		}else{
			
			//display the form again with values entred by the user
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.SELLER, seller);
			this.getServletContext().getRequestDispatcher(Name.ADD_SELLER_FORM).forward(request,response);
			
		}
	}

	private void updateSellerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SellerForm form = new SellerForm();
		Seller seller = form.addSeller(request);
		
		
		if(form.getResult().equals("true")){
			
			Seller rseller = (Seller)request.getSession().getAttribute(Name.ACCOUNT);
			
			rseller.setEmail(seller.getEmail());
			rseller.setPassword(seller.getPassword());
			rseller.setPhone(seller.getPhone());
			rseller.getPerson().setBirth(seller.getPerson().getBirth());
			rseller.getPerson().setFirstname(seller.getPerson().getFirstname());
			rseller.getPerson().setLastname(seller.getPerson().getLastname());
			rseller.getPerson().setGender(seller.getPerson().getGender());
			rseller.getInfo().setLastModifiedDate(new Date());
			rseller.setSeller_name(seller.getSeller_name());
			rseller.getInfo().setSeller_url(seller.getInfo().getSeller_url());
		
			//update the seller and put it in the session scopeS
			rseller = ss.update(rseller);
			request.getSession().setAttribute(Name.ACCOUNT,rseller);
	
			//redirect to the home page
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			
		}else{
			
			//display the form with the information entred by the user
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.SELLER, seller);
			this.getServletContext().getRequestDispatcher(Name.UPDATE_SELLER_FORM).forward(request,response);
		
		}
	}


}
