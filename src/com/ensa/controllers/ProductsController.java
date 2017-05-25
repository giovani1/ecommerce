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
import javax.servlet.http.HttpSession;

import com.ensa.forms.ProductForm;
import com.ensa.forms.ProductForm.source;
import com.ensa.models.Categorie;
import com.ensa.models.Product;
import com.ensa.models.Seller;
import com.ensa.service.CategorieService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;
import com.ensa.service.Product_optionsService;
import com.ensa.util.Cons;

/**
 * Servlet implementation class GetProduct
 */
@WebServlet(value={"/c/*","/s/*","/products"})
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_PRODUCTS_REGULAR		= "/WEB-INF/views/products.jsp";
	public static final String VUE_PRODUCTS_ADMIN		= "/WEB-INF/views/productsadmin.jsp";
	public static final String VUE_PRODUCTS_SELLER		= "/WEB-INF/views/productsseller.jsp";
    
	@EJB
    ProductService ps;
    @EJB
    CategorieService cs;
    @EJB
    Product_optionsService pos;
    @EJB
    Product_attributesService pas;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session=request.getSession();
		List<Product> products=new ArrayList<Product>();
		//String accountType=(String) session.getAttribute("accountType");
		String accountType="";
		ProductForm form=new ProductForm(ps, cs,pos,pas);
		source s=form.getSource(request);
		
		if(s.s=="seller"){
			//Seller seller=(Seller) session.getAttribute("account");
			Seller seller=null;
			if(accountType=="seller"){
				products=ps.findBySeller(seller.getId());
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_SELLER ).forward( request, response );
			}else if(accountType=="admin"){
				products=ps.findBySeller(seller.getId());
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_ADMIN ).forward( request, response );
			}else{
				products=ps.findBySeller(seller.getId(),true);
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_REGULAR ).forward( request, response );
			}
		}else if(s.s=="categorie"){
			//for the breadcrump
			ArrayList<Categorie> categories=new ArrayList<>();
			Categorie categorie=cs.find(s.id);
			List<Categorie> list=new ArrayList<Categorie>();
			list.add(categorie);
			while(categorie!=null){
				categories.add(categorie);
				categorie=categorie.getParent();
			}
			//get all categories under the target

			for(int i=0;i<list.size();i++){
				List<Categorie> list1=cs.findByParent(list.get(i).getId());
				if(list1!=null){
					list.addAll(list1);
				}	
			}
			
			
			if(accountType=="admin"){
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getName());
					products.addAll(ps.findByCategory(list.get(i).getId()));
				}
				request.setAttribute(Cons.ATT_CATEGORIE,categories);
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_ADMIN ).forward( request, response );
			}else{
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getName());
					products.addAll(ps.findByCategory(list.get(i).getId()));
				}
				products=ps.findByCategory(s.id,true);
				request.setAttribute(Cons.ATT_CATEGORIE,categories);
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_REGULAR ).forward( request, response );
			}
		}
	}
}
