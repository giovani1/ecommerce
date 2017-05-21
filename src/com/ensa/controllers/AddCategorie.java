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

import com.ensa.forms.CategorieForm;
import com.ensa.models.Categorie;
import com.ensa.service.CategorieService;
import com.ensa.util.Cons;

@WebServlet(value={"/categorie","/categorie/add","/categorie/update","/categorie/delete"})
@MultipartConfig
public class AddCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ADD_FORM = 			"/WEB-INF/views/admin/categorie.jsp";
	private static final String UPDATE_FORM = 		"/WEB-INF/views/admin/updatecategorie.jsp";
	private static final String VUE_LIST = 			"/WEB-INF/views/admin/categorie.jsp";
     
	@EJB
	CategorieService cs;
	
	@Override
	public void init() throws ServletException {
		updateCategories();
		super.init();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getServletPath().equals("/categorie")){
			this.getServletContext().getRequestDispatcher( VUE_LIST ).forward( request, response );
		}
		else if(request.getServletPath().equals("/categorie/add")){
			this.getServletContext().getRequestDispatcher( ADD_FORM ).forward( request, response );
		}
		else if(request.getServletPath().equals("/categorie/update")){
			CategorieForm form=new CategorieForm(cs);
			Categorie categorie=form.getCategorie(request);
			if(form.getResult()=="true"){
				request.setAttribute(Cons.ATT_CATEGORIE,categorie);
				this.getServletContext().getRequestDispatcher( UPDATE_FORM ).forward( request, response );
			}
			else{
				response.sendRedirect(this.getServletContext().getContextPath()+"/categorie");
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/categorie/add")){
			CategorieForm form=new CategorieForm(cs);
			Categorie categorie = form.addCategorie(request);
			if(form.getResult()=="true"){
				categorie=cs.add(categorie);
				categorie = form.addCategorie1(request,categorie);
				if(form.getResult()=="true"){
					System.out.println("hollllllll");
					categorie=cs.update(categorie);
					updateCategories();
					response.sendRedirect("/categorie");
				}
				else{
					response.sendRedirect(this.getServletContext().getContextPath()+"/categorie/update?"+categorie.getId());
				}
			}else{
				request.setAttribute(Cons.ATT_CATEGORIE,categorie);
				request.setAttribute(Cons.ATT_FORM,form);
				this.getServletContext().getRequestDispatcher( ADD_FORM ).forward( request, response );
			}
		}
		else if(request.getServletPath().equals("/categorie/update")){
			CategorieForm form=new CategorieForm(cs);
			Categorie categorie = null;
			categorie = form.updateCategorie(request);
			if(form.getResult()=="true"){
				cs.update(categorie);
				updateCategories();
				response.sendRedirect(this.getServletContext().getContextPath()+"/categorie");
			}else{
				request.setAttribute(Cons.ATT_CATEGORIE,categorie);
				request.setAttribute(Cons.ATT_FORM,form);
				this.getServletContext().getRequestDispatcher( UPDATE_FORM ).forward( request, response );
			}
		}else if(request.getServletPath().equals("/categorie/delete")){
			CategorieForm form=new CategorieForm(cs);
			Categorie categorie=null;
			categorie=form.deleteCategorie(request);
			if(form.getResult()=="true"){
				cs.remove(categorie);
				updateCategories();
				response.sendRedirect(this.getServletContext().getContextPath()+"/categorie");
			}else{
				response.sendRedirect(this.getServletContext().getContextPath()+"/categorie");
			}
		}	
	}
	
	public void updateCategories(){
		List<Categorie> categories=cs.findAll();
		Map<Integer,Categorie> categoriess=new HashMap<Integer,Categorie>();
		for(int i=0;i<categories.size();i++){
			categoriess.put(categories.get(i).getId(), categories.get(i));
		}
		this.getServletContext().setAttribute(Cons.ATT_CATEGORIES, categoriess);
	}
}