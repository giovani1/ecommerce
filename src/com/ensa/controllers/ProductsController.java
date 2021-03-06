package com.ensa.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.ProductForm;
import com.ensa.models.Categorie;
import com.ensa.models.Product;
import com.ensa.models.Seller;
import com.ensa.service.CategorieService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;
import com.ensa.service.Product_optionsService;
import com.ensa.service.SellerService;
import com.ensa.util.Cons;

/**
 * Servlet implementation class GetProduct
 */
@WebServlet(value={"/c/*","/s/*","/products"})
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_PRODUCTS_REGULAR		= "/WEB-INF/views/products.jsp";
	public static final String VUE_PRODUCTS_ADMIN		= "/WEB-INF/views/admin/productsadmin.jsp";
	public static final String VUE_PRODUCTS_SELLER		= "/WEB-INF/views/seller/productsseller.jsp";
    
	@EJB
	SellerService ss;
	@EJB
    ProductService ps;
    @EJB
    CategorieService cs;
    @EJB
    Product_optionsService pos;
    @EJB
    Product_attributesService pas;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		List<Product> products=new ArrayList<Product>();
		System.out.println(request.getServletPath());
		if(request.getServletPath().equals("/s")){
			ProductForm form=new ProductForm(ps, cs,pos,pas);
			int id=form.getProducts(request);
			if(form.getResult()=="true" && id!=-1){
				products=ps.findBySeller(id);
				request.setAttribute("sellers",((List<Seller>)ss.getAllSellers()));
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				request.setAttribute("sellerOf",products.get(0).getSeller());
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_REGULAR ).forward( request, response );
			}
			
		}else if(request.getServletPath().equals("/c")){
			ProductForm form=new ProductForm(ps, cs,pos,pas);
			int id=form.getProducts(request);
			if(form.getResult()=="true" && id!=-1){
				ArrayList<Categorie> categories=new ArrayList<>();
				Categorie categorie=cs.find(id);
				List<Categorie> categoriess = cs.findByParent(id);
				List<Categorie> list=new ArrayList<Categorie>();
				list.add(categorie);
				while(categorie!=null){
					categories.add(categorie);
					categorie=categorie.getParent();
				}
				Collections.reverse(categories);
				//get all categories under the target

				for(int i=0;i<list.size();i++){
					List<Categorie> list1=cs.findByParent(list.get(i).getId());
					if(list1!=null){
						list.addAll(list1);
					}	
				}
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getName());
					products.addAll(ps.findByCategory(list.get(i).getId()));
				}
				request.setAttribute(Cons.ATT_CATEGORIE,categories);
				request.setAttribute("categories",categoriess);
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_REGULAR ).forward( request, response );
			}
			
		}else if(request.getServletPath().equals("/products")){
			String accountType=(String) session.getAttribute("accountType");
			if(accountType=="seller"){
				Seller seller=(Seller) session.getAttribute("account");
				products=ps.findBySeller(seller.getId());
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_SELLER ).forward( request, response );
			}else if(accountType=="admin"){
				products=ps.findAll();
				request.setAttribute(Cons.ATT_PRODUCTS,products);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCTS_ADMIN ).forward( request, response );
			}
		}
		
		/*ProductForm form=new ProductForm(ps, cs,pos,pas);
		source s=form.getSource(request);
		
		if(s.s=="seller"){
			Seller seller=(Seller) session.getAttribute("account");
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
		}*/
	}
}
