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
import com.ensa.models.Review;
import com.ensa.service.CategorieService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;
import com.ensa.service.Product_optionsService;
import com.ensa.util.Cons;

/**
 * Servlet implementation class GetProduct
 */
@WebServlet(value={"/p/*","/product/"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_PRODUCT_REGULAR	= "/WEB-INF/views/product.jsp";
	public static final String VUE_PRODUCT_ADMIN	= "/WEB-INF/views/admin/productadmin.jsp";
	public static final String VUE_PRODUCT_SELLER	= "/WEB-INF/views/seller/productseller.jsp";

	@EJB
    ProductService ps;
	@EJB
    CategorieService cs;
	@EJB
	Product_attributesService pas;
	@EJB
	Product_optionsService pos;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String accountType=(String) session.getAttribute("accountType");
		ProductForm form =new ProductForm(ps, cs, pos,pas);
		Product product=form.getProduct(request);
		if(form.getResult()=="true"){
			ArrayList<Categorie> categorie=new ArrayList<>();
			Categorie c=product.getCategorie();
			while(c!=null){
				
				categorie.add(c);
				c=c.getParent();
			} 
			Collections.reverse(categorie);
			request.setAttribute(Cons.ATT_CATEGORIE,categorie);
			request.setAttribute(Cons.ATT_PRODUCT,product);
			if(accountType=="seller"){
				this.getServletContext().getRequestDispatcher( VUE_PRODUCT_SELLER ).forward( request, response );
			}else if(accountType=="admin"){
				this.getServletContext().getRequestDispatcher( VUE_PRODUCT_ADMIN ).forward( request, response );
			}
			else{
				/* calculating the ratio */
				float ratio = 0;
				
				List<Review> prL = product.getReviews();
				int nbFalse=0;
				int size = prL.size();
				if(prL != null){
					float sum = 0;
					for(int i=0;i<size;i++){
						if(prL.get(i).getStatus())
							sum += prL.get(i).getRating();
						else 
							nbFalse++;
					}
					ratio = (sum/(size-nbFalse));
				}
				request.setAttribute("nbFalse", nbFalse);
				request.setAttribute("ratio", ratio);
				this.getServletContext().getRequestDispatcher( VUE_PRODUCT_REGULAR ).forward( request, response );
			}
		}
		else
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
}
