package com.ensa.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensa.models.Client;
import com.ensa.service.ClientService;

/**
 * Servlet implementation class AllClient
 */
@WebServlet(value={"/AllClients","/AllClients/switch_status"})
public class AllClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String ALL_CLIENTS ="/WEB-INF/views/admin/allClients.jsp";   
    
    @EJB
    ClientService cs;
    
    public AllClient() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> clients= cs.getAllClients();
		System.out.println(clients.size());
		request.setAttribute("clients",clients);
		this.getServletContext().getRequestDispatcher(ALL_CLIENTS).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/AllClients/switch_status")){
			int client_id = Integer.parseInt(request.getParameter("client_id"));
			Client client = cs.getClientById(client_id);

			if(client.getStatus())
				client.setStatus(false);
			else
				client.setStatus(true);
			
			cs.update(client);
			
			response.sendRedirect(this.getServletContext().getContextPath()+"/AllClients");
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+"/AllClients");
		}
	}

}
