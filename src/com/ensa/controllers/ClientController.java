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

import com.ensa.forms.ClientForm;
import com.ensa.models.Client;
import com.ensa.service.ClientService;
import com.ensa.util.Name;


@WebServlet(value={"/client","/client/add","/client/update"})
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    
    @EJB
    ClientService cs;
    
    public ClientController() {
        super();
        
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/client/add")){
			addClientGet(request,response);
		}else if(request.getServletPath().equals("/client/update")){
			UpdateClientGet(request,response);
		}else if(request.getServletPath().equals("/client")){
			clientProfil(request,response);
		}
		else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/client/add")){
			addClientPost(request,response);
		}else if(request.getServletPath().equals("/client/update")){
			updateClientPost(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void clientProfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute(Name.ACCOUNT) != null && ((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client") ){
			Client client = (Client)session.getAttribute(Name.ACCOUNT);
			request.setAttribute(Name.CLIENT, client);
			this.getServletContext().getRequestDispatcher(Name.PROFIL_CLIENT_PAGE).forward(request,response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void addClientPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//validate the form values
		ClientForm  form = new ClientForm();	
		Client client = form.addClient(request);
		
		if(form.getResult().equals("true")){
			
			//set client info 
			client.getInfo().setLastModifiedDate(new Date());
			client.getInfo().setLastLoginDate(new Date());
			client.getInfo().setRegistratioDate(new Date());
			client.getInfo().setNumbers_logons(1);
			client.setStatus(true);
			try{
				if(cs.getClient(client.getEmail()) == null){
					client = cs.add(client);
					System.out.println("||the client added");
					request.getSession().setAttribute(Name.ACCOUNT, client);
					request.getSession().setAttribute(Name.ACCOUNT_TYPE, "client");

					response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
					
				}else{
				
					request.setAttribute(Name.FORM, form);
					request.setAttribute(Name.CLIENT, client);
					request.setAttribute(Name.USED_EMAIL,"l'email que vous avez entrez est deja utilise , veuillez choisir une autre adresse email");
					this.getServletContext().getRequestDispatcher(Name.ADD_CLIENT_FORM).forward(request,response);
	
				}
				
				
			}catch(EJBException e){
				
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			
			}
			
			
		
		}else{
			
			//display the form with the errors attached to it when some exception occurs
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.CLIENT, client);
			
			this.getServletContext().getRequestDispatcher(Name.ADD_CLIENT_FORM).forward(request,response);
		
		}
	}
	
	private void updateClientPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//verifing the form using the addClientForm because add and update had the same fields
		ClientForm  form = new ClientForm();	
		Client client = form.addClient(request);
		
		if(form.getResult().equals("true")){
			Client rclient = (Client)request.getSession().getAttribute(Name.ACCOUNT);
			
			rclient.setEmail(client.getEmail());
			rclient.setPassword(client.getPassword());
			rclient.setPhone(client.getPhone());
			rclient.getPerson().setBirth(client.getPerson().getBirth());
			rclient.getPerson().setFirstname(client.getPerson().getFirstname());
			rclient.getPerson().setLastname(client.getPerson().getLastname());
			rclient.getPerson().setGender(client.getPerson().getGender());
			rclient.getInfo().setLastModifiedDate(new Date());
			
			rclient = cs.update(rclient);
			request.getSession().setAttribute(Name.ACCOUNT,rclient);
			
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
			
		
		}else{
		
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.CLIENT, client);
			this.getServletContext().getRequestDispatcher(Name.UPDATE_CLIENT_FORM).forward(request,response);
		
		}
	}
	
	private void addClientGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getSession().getAttribute(Name.ACCOUNT) == null){
			this.getServletContext().getRequestDispatcher(Name.ADD_CLIENT_FORM).forward(request, response);
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		}
	}
	
	private void UpdateClientGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		//check if the client is logged 
		if(session.getAttribute(Name.ACCOUNT_TYPE) != null && ((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("client") ){
			
			Client client =(Client)session.getAttribute(Name.ACCOUNT);
			
			//fill old information in a new client object
			Client clientUpdate = new Client();
			
			clientUpdate.setEmail(client.getEmail());
			clientUpdate.setPerson(client.getPerson());;
			clientUpdate.setPassword(client.getPassword());
			clientUpdate.setPhone(client.getPhone());
			
			//set the information in the request 
			request.setAttribute(Name.CLIENT, clientUpdate);
			
			//display the form with old values
			this.getServletContext().getRequestDispatcher(Name.UPDATE_CLIENT_FORM).forward(request,response);
			
		}else{
			
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);
		
		}
	}
}
