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

import com.ensa.forms.AdresseForm;
import com.ensa.models.Adresse;
import com.ensa.models.Client;
import com.ensa.models.Seller;
import com.ensa.service.AdressService;
import com.ensa.service.ClientService;
import com.ensa.service.SellerService;
import com.ensa.util.Name;


@WebServlet(value={"/addresse","/addresse/add","/addresse/update"})
public class AdressController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    
	@EJB
	SellerService ss;
    @EJB
    ClientService cs;
    @EJB
    AdressService as;
    
    public AdressController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/addresse/add")){
			addAdresseGet(request,response);
		}else if(request.getServletPath().equals("/addresse/update")){
			updateAdresseGet(request,response);
		}else if(request.getServletPath().equals("/addresse")){
			showAllAddress(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/addresse/add")){
			addAdressePost(request,response);
		}else if(request.getServletPath().equals("/addresse/update")){
			updateAdressePost(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void showAllAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute(Name.ACCOUNT) != null){
			if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
				session.setAttribute(Name.CLIENT, (Client)request.getSession().getAttribute(Name.ACCOUNT));
				this.getServletContext().getRequestDispatcher(Name.ALL_CLIENT_ADDRESSES).forward(request, response);
			}else if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("seller")){
				session.setAttribute(Name.SELLER, (Seller)request.getSession().getAttribute(Name.ACCOUNT));
				this.getServletContext().getRequestDispatcher(Name.ALL_SELLER_ADDRESSES).forward(request, response);
			}
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void addAdresseGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		if(request.getSession().getAttribute(Name.ACCOUNT) != null){
			this.getServletContext().getRequestDispatcher(Name.ADD_ADRESSE_FORM).forward(request, response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
		
	}

	private void updateAdresseGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int addressId ;
		HttpSession session = request.getSession();
		
		if(session.getAttribute(Name.ACCOUNT) != null){
			
			try{
				if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
					Client client = (Client)request.getSession().getAttribute(Name.ACCOUNT);
					addressId = Integer.parseInt(request.getParameter("id"));
					
					Adresse address = as.getAdresse(addressId);
					if(address != null){
						if(address.getPerson().getId() == client.getPerson().getId()){
							
							Adresse newAdd = new Adresse();
							newAdd.setAdress(address.getAdress());
							newAdd.setCity(address.getCity());
							newAdd.setZipCode(address.getZipCode());
							newAdd.setPerson(address.getPerson());
							newAdd.setId(address.getId());
							
							request.setAttribute(Name.ADRESS, newAdd);
							request.getSession().setAttribute(Name.ID_ADDRESS_TO_UPDATE,addressId);
							System.out.println(newAdd);
							this.getServletContext().getRequestDispatcher(Name.UPDATE_ADRESSE_FORM).forward(request,response);
						
						}else{
							response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
						}
					}else{
						response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
					}
				}else if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("seller") == true){
					Seller seller = (Seller)session.getAttribute(Name.ACCOUNT);
					addressId = Integer.parseInt(request.getParameter("id"));
					Adresse address = as.getAdresse(addressId);
					if(address != null){
						if(address.getPerson().getId() == seller.getPerson().getId()){
							
							Adresse newAdd = new Adresse();
							newAdd.setAdress(address.getAdress());
							newAdd.setCity(address.getCity());
							newAdd.setZipCode(address.getZipCode());
							newAdd.setPerson(address.getPerson());
							newAdd.setId(address.getId());
							
							request.setAttribute(Name.ADRESS, newAdd);
							request.getSession().setAttribute(Name.ID_ADDRESS_TO_UPDATE,addressId);
							System.out.println(newAdd);
							this.getServletContext().getRequestDispatcher(Name.UPDATE_ADRESSE_FORM).forward(request,response);
						
						}else{
							response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
						}
					}else{
						response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
					}
				}
			}catch(Exception e){
				
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			}
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}

	private void addAdressePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdresseForm form = new AdresseForm();
		Adresse adress = form.addAdresse(request);
		
		
		if(form.getResult().equals("true")){
			HttpSession session = request.getSession();
			if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
			
				Client client  = (Client)request.getSession().getAttribute(Name.ACCOUNT);
				client = as.AddAdressToClient(adress, client.getId());
				request.getSession().setAttribute(Name.ACCOUNT, cs.getClientById(client.getId()));
				response.sendRedirect(this.getServletContext().getContextPath()+Name.ADDRESSES);
			
			}else if (((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("seller")){
				Seller seller = (Seller)request.getSession().getAttribute(Name.ACCOUNT);
				seller = as.AddAdressToSeller(adress, seller.getId());
				request.getSession().setAttribute(Name.ACCOUNT, ss.getSeller(seller.getId()));
				response.sendRedirect(this.getServletContext().getContextPath()+Name.ADDRESSES);
			}		
			
		}else{
			
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.ADRESS, adress);
			this.getServletContext().getRequestDispatcher(Name.ADD_ADRESSE_FORM).forward(request,response);
		
		}
	}

	private void updateAdressePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AdresseForm form = new AdresseForm();
		Adresse adress = form.addAdresse(request);
		
		
		if(form.getResult().equals("true")){
			HttpSession session = request.getSession();
			if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
				adress.setPerson(((Client)session.getAttribute(Name.ACCOUNT)).getPerson());
				adress.setId((Integer)session.getAttribute(Name.ID_ADDRESS_TO_UPDATE));
				as.update(adress);
				
				((Client)session.getAttribute(Name.ACCOUNT)).getInfo().setLastModifiedDate(new Date());
				session.setAttribute(Name.ACCOUNT,cs.getClientById(((Client)session.getAttribute(Name.ACCOUNT)).getId()));
				request.getSession().removeAttribute(Name.ID_ADDRESS_TO_UPDATE);
				response.sendRedirect(this.getServletContext().getContextPath()+Name.ADDRESSES);
			}else if(((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("seller")){
				adress.setPerson(((Seller)session.getAttribute(Name.ACCOUNT)).getPerson());
				adress.setId((Integer)session.getAttribute(Name.ID_ADDRESS_TO_UPDATE));
				as.update(adress);
				
				((Seller)session.getAttribute(Name.ACCOUNT)).getInfo().setLastModifiedDate(new Date());
				session.setAttribute(Name.ACCOUNT,ss.getSeller(((Seller)session.getAttribute(Name.ACCOUNT)).getId()));
				request.getSession().removeAttribute(Name.ID_ADDRESS_TO_UPDATE);
				response.sendRedirect(this.getServletContext().getContextPath()+Name.ADDRESSES);
			}
		}else{
			
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.ADRESS, adress);
			this.getServletContext().getRequestDispatcher(Name.UPDATE_ADRESSE_FORM).forward(request,response);
		
		}
	}
}
