package com.ensa.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.ProductForm;
import com.ensa.models.Categorie;
import com.ensa.models.Product;
import com.ensa.models.Product_attributes;
import com.ensa.models.Product_options;
import com.ensa.models.Seller;
import com.ensa.service.CategorieService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;
import com.ensa.service.Product_optionsService;
import com.ensa.service.SellerService;
import com.ensa.util.Cons;
import com.ensa.util.Name;

@WebServlet(value={"/product/add","/product/update","/product/delete","/product/block",
		"/product/c/update","/product/o/add","/product/o/update","/product/o/delete"})
@MultipartConfig
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private static final String ADD_FORM 		= "/WEB-INF/views/seller/addproduct.jsp";
	private static final String UPDATE_FORM 	= "/WEB-INF/views/updateproduct.jsp";

    @EJB
    ProductService ps;
    @EJB
    CategorieService cs;
    @EJB
    Product_optionsService pos;
    @EJB
    Product_attributesService pas;
   
    @EJB
    SellerService ss;
    
	@Override
	public void init() throws ServletException {
		List<Categorie> categories=cs.findAll();
		Map<Integer,Categorie> categoriess=new HashMap<Integer,Categorie>();
		for(int i=0;i<categories.size();i++){
			categoriess.put(categories.get(i).getId(), categories.get(i));
		}
		this.getServletContext().setAttribute(Cons.ATT_CATEGORIES, categoriess);
		List<Product_options> options=pos.findAll();
		Map<Integer,Product_options> optionss=new HashMap<Integer,Product_options>();
		for(int i=0;i<options.size();i++){
			optionss.put(options.get(i).getId(), options.get(i));
		}
		this.getServletContext().setAttribute(Cons.ATT_OPTIONS, optionss);
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/product/add")){		
				this.getServletContext().getRequestDispatcher( ADD_FORM ).forward( request, response );
		}
		else if(request.getServletPath().equals("/product/update")){
			ProductForm form =new ProductForm(ps,cs,pos,pas);
			Product product=form.getProduct1(request);
			if(form.getResult()=="true"){
				request.setAttribute(Cons.ATT_PRODUCT,product);
				this.getServletContext().getRequestDispatcher( UPDATE_FORM ).forward( request, response );
			}else{
				response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
			}
		}else if(request.getServletPath().equals("/product/block")){
			ProductForm form =new ProductForm(ps,cs,pos,pas);
			Product product=form.blockProduct(request);
			if(form.getResult()=="true"){
				product=ps.update(product);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(request.getServletPath().equals("/product/add")){
			Seller seller=(Seller)session.getAttribute(Name.ACCOUNT);
			//Seller seller=null;
			ProductForm form =new ProductForm(ps,cs,pos,pas);
			Product product = form.addProduct(request);
			if(form.getResult()=="true"){
				product.setSeller(seller);
				product.setStatus(false);
				product = ps.add(product);
				ss.addProductToSeller(product.getId(),seller.getId());
				session.setAttribute(Name.ACCOUNT,ss.getSeller(seller.getId()));
				
				product=form.addProduct1(request, product);
				if(form.getResult()=="true"){
					product.setStatus(true);
					ps.update(product);
					response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
				}else{
					response.sendRedirect(this.getServletContext().getContextPath()+"/product/update?"+product.getId());
				}
			}else{
				request.setAttribute(Cons.ATT_PRODUCT,product);
				request.setAttribute(Cons.ATT_FORM,form);
				this.getServletContext().getRequestDispatcher( ADD_FORM ).forward( request, response );
			}
		}else if(request.getServletPath().equals("/product/update")){
			ProductForm form=new ProductForm(ps, cs, pos,pas);
			Product product = form.updateProduct(request);
			product=ps.update(product);
			response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
			
		}else if(request.getServletPath().equals("/product/c/update")){
			ProductForm form=new ProductForm(ps, cs, pos,pas);
			Product product = form.updateCategorieProduct(request);
			if(form.getResult()=="true"){
				product=ps.update(product);	
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
		}else if(request.getServletPath().equals("/product/o/add")){
			ProductForm form=new ProductForm(ps, cs, pos,pas);
			Product product = form.addAttributesProduct(request);
			if(form.getResult()=="true"){
				ps.update(product);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
		}else if(request.getServletPath().equals("/product/o/update")){
			ProductForm form=new ProductForm(ps, cs, pos,pas);
			Product_attributes attributes = form.updateAttributesProduct(request);
			if(form.getResult()=="true"){
				attributes=pas.update(attributes);
			}else{
				response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+attributes.getProduct().getUrl());
			}
			
		}else if(request.getServletPath().equals("/product/o/delete")){
			ProductForm form=new ProductForm(ps, cs, pos,pas);
			Product_attributes attributes = form.deleteAttributesProduct(request);
			Product product=attributes.getProduct();
			if(form.getResult()=="true"){
				List<Product_attributes> list=product.getProducts_attributes();
				list.remove(attributes);
				product.setProducts_attributes(list);
				ps.update(product);
				//pas.remove(attributes);
				//ps.update(attributes.getProduct());
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+attributes.getProduct().getUrl());
		}else if(request.getServletPath().equals("/product/delete")){
			ProductForm form=new ProductForm(ps, cs,pos,pas);
			Product product = form.deleteProduct(request);
			if(form.getResult()=="true"){
				ps.remove(product);
				response.sendRedirect(this.getServletContext().getContextPath()+"/Home");
			}else{
				response.sendRedirect(this.getServletContext().getContextPath()+"/p/"+product.getUrl());
			}
		}
		
	}
}
