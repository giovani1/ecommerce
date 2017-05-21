package com.ensa.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Product_options;
import com.ensa.models.Product_options_value;
import com.ensa.service.Product_optionsService;
import com.ensa.util.Cons;
import com.ensa.util.Validation;

public class ProductOptionsForm extends Form{
	
	private Product_optionsService pos;
	
	public ProductOptionsForm(Product_optionsService pos) {
		this.pos=pos;
	}
	public Product_options AddProductOptions(HttpServletRequest request){
		String name = getValue(request,Cons.OPTION_NAME_FIELD);
		Product_options product_options=new Product_options();
		try{
			Validation.validateName(name);
			product_options.setName(name);
		} catch(Exception e){
			setError(Cons.OPTION_NAME_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return product_options;
	}
	public Product_options updateProductOptions(HttpServletRequest request){
		String name = getValue(request,Cons.OPTION_NAME_FIELD);
		String id = getValue(request,Cons.OPTION_ID_FIELD);
		Product_options product_options=null;
		try{
			Validation.validateId(id);
			product_options=pos.find(Integer.parseInt(id));
		}catch(Exception e){
			System.out.println(e.getMessage());
			setError(Cons.OPTION_ID_FIELD,e.getMessage());
		}
		
		try{
			Validation.validateName(name);
		} catch(Exception e){
			System.out.println(e.getMessage());
			setError(Cons.OPTION_NAME_FIELD,e.getMessage());
		}
		product_options.setName(name);
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return product_options;
	}
	public Product_options deleteProduct_options(HttpServletRequest request){
		String id = getValue(request,Cons.OPTION_ID_FIELD);
		Product_options product_options=null;
		try{
			Validation.validateId(id);
			product_options=pos.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.OPTION_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return product_options;
	}
	public Product_options addProduct_options_value(HttpServletRequest request) {
		String id = getValue(request,Cons.OPTION_ID_FIELD);
		String name = getValue(request,Cons.VALUE_NAME_FIELD);
		Product_options_value pov=new Product_options_value();
		Product_options po=null;
		try{
			Validation.validateId(id);
			po=pos.find(Integer.parseInt(id));
			try{
				Validation.validateName(name);
				pov.setName(name);
			} catch(Exception e){
				setError(Cons.VALUE_NAME_FIELD,e.getMessage());
			}
		}catch(Exception e){
			setError(Cons.OPTION_ID_FIELD,e.getMessage());
		}
		
		if(errors.isEmpty()){
			List<Product_options_value> x=po.getProduct_options_value();
			x.add(pov);
			po.setProduct_options_value(x);
			pov.setProduct_options(po);
			result="true";
		}
		else
			result="false";
		return po;
	}
	public Product_options_value updateProduct_options_value(HttpServletRequest request) {
		String id = getValue(request,Cons.VALUE_ID_FIELD);
		String name = getValue(request,Cons.VALUE_NAME_FIELD);
		Product_options_value pov=null;
		try{
			Validation.validateId(id);
			pov=pos.findValue(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.OPTION_ID_FIELD,e.getMessage());
		}
		try{
			Validation.validateName(name);
			pov.setName(name);
		} catch(Exception e){
			setError(Cons.VALUE_NAME_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return pov;
	}
	public Product_options_value deleteProduct_options_value(HttpServletRequest request) {
		String id = getValue(request,Cons.VALUE_ID_FIELD);
		Product_options_value pov=null;
		try{
			Validation.validateId(id);
			pov=pos.findValue(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.VALUE_ID_FIELD,e.getMessage());
		}
		
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return pov;
	}
}
