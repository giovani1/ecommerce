package com.ensa.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensa.models.Seller;
import com.ensa.service.SellerService;

/**
 * Servlet implementation class AllSeller
 */
@WebServlet(value={"/AllSellers","/AllSellers/switch_status"})
public class AllSeller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ALL_SELLERS ="/WEB-INF/views/admin/allSellers.jsp";  
	
	@EJB
	SellerService ss;
  
    public AllSeller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Seller> sellers= ss.getAllSellers();
		System.out.println(sellers.size());
		if(sellers.isEmpty()){
			sellers = new ArrayList<Seller>();
		}
		request.setAttribute("sellers",sellers);
		this.getServletContext().getRequestDispatcher(ALL_SELLERS).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getServletPath().equals("/AllSellers/switch_status")){
			int sellerId = Integer.parseInt(request.getParameter("seller_id"));
			Seller seller = ss.getSeller(sellerId);
			if(seller.getStatus())
				seller.setStatus(false);
			else
				seller.setStatus(true);

			ss.update(seller);
			
			response.sendRedirect(this.getServletContext().getContextPath()+"/AllSellers");
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+"/AllSellers");
		}
	}

}
