package com.ensa.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensa.forms.ProductOptionsForm;
import com.ensa.models.Product_options;
import com.ensa.models.Product_options_value;
import com.ensa.service.Product_optionsService;
import com.ensa.util.Cons;

/**
 * Servlet implementation class Options
 */
@WebServlet(value={"/options","/options/add","/options/update","/options/delete",
		"/optionsvalue/add","/optionsvalue/update","/optionsvalue/delete"})

public class OptionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_FORM = "/WEB-INF/views/admin/options.jsp";
    
	@EJB
	Product_optionsService pos;
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		updateOptions();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/options")){
			System.out.println(this.getServletContext().getRealPath("/resources/img"));
			updateOptions();
			this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/options/add")){
			ProductOptionsForm form=new ProductOptionsForm(pos);
			Product_options options = null;
			options = form.AddProductOptions(request);
			if(form.getResult()=="true"){
				pos.add(options);
				//updateOptions();
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/options");
		}else if(request.getServletPath().equals("/options/update")){
			ProductOptionsForm form1 =new ProductOptionsForm(pos);
			Product_options productOptions = null;
			productOptions = form1.updateProductOptions(request);
			if(form1.getResult()=="true"){
				pos.update(productOptions);
				//updateOptions();
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/options");
		}else if(request.getServletPath().equals("/options/delete")){
			ProductOptionsForm form1 =new ProductOptionsForm(pos);
			Product_options productOptions = null;
			productOptions = form1.deleteProduct_options(request);
			if(form1.getResult()=="true"){
				pos.remove(productOptions);
				//updateOptions();
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/options");
		}else if(request.getServletPath().equals("/optionsvalue/add")){
			ProductOptionsForm form1 =new ProductOptionsForm(pos);
			Product_options po = form1.addProduct_options_value(request);
			if(form1.getResult()=="true"){
				pos.update(po);
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/options");
		}else if(request.getServletPath().equals("/optionsvalue/update")){
			ProductOptionsForm form1 =new ProductOptionsForm(pos);
			Product_options_value pov = null;
			pov = form1.updateProduct_options_value(request);
			if(form1.getResult()=="true"){
				pos.updateValue(pov);
				//updateOptions();
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/options");
		}else if(request.getServletPath().equals("/optionsvalue/delete")){
			ProductOptionsForm form1 =new ProductOptionsForm(pos);
			Product_options_value pov = null;
			pov = form1.deleteProduct_options_value(request);
			if(form1.getResult()=="true"){
				pos.removeValue(pov);
				//updateOptions();
			}
			response.sendRedirect(this.getServletContext().getContextPath()+"/options");
		}
		
	}
	
	public void updateOptions(){
		List<Product_options> options=pos.findAll();
		Map<Integer,Product_options> optionss=new HashMap<Integer,Product_options>();
		for(int i=0;i<options.size();i++){
			optionss.put(options.get(i).getId(), options.get(i));
		}
		this.getServletContext().setAttribute(Cons.ATT_OPTIONS, optionss);
	}
}
