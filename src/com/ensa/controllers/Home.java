package com.ensa.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensa.models.Categorie;
import com.ensa.models.Product;
import com.ensa.service.CategorieService;
import com.ensa.service.ProductService;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String HOME = "/WEB-INF/views/index.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    @EJB
    ProductService ps;
    @EJB
    CategorieService cs;
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Product> products = ps.findAll();
		Collections.reverse(products);
		request.setAttribute("products",products);
		this.getServletContext().getRequestDispatcher(HOME).forward(request,response);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
