package com.ensa.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Cart;
import com.ensa.models.Cart_attributes;
import com.ensa.models.Client;
import com.ensa.models.Product;
import com.ensa.models.Product_attributes;
import com.ensa.service.CartService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;
import com.ensa.util.Cons;
import com.ensa.util.Validation;

public class CartForm extends Form {
	private String s2="(attributes_)([0-9]+)$";
	
	private CartService cs;
	private ProductService ps;
	private Product_attributesService pas;
	
	
	public CartForm(CartService cs, ProductService ps,Product_attributesService pas) {
		this.cs = cs;
		this.ps = ps;
		this.pas=pas;
	}

	public Cart addCart(HttpServletRequest request,Client client){
		String product_id = getValue(request,Cons.PRODUCT_ID_FIELD);
		String quantity = getValue(request,Cons.PRODUCT_QUANTITY_FIELD);
		Enumeration<String> attributes=request.getParameterNames();
		
		Cart cart=new Cart();
		Product p=null;
		try{
			Validation.validateId(product_id);
			p=ps.find(Integer.parseInt(product_id));
			cart.setProduct(p);
			cart.setDate_added(new Date());
			cart.setClient(client);
			try{
				Validation.validateQuantity(quantity);
				if(Integer.parseInt(quantity)<=p.getQuantity() && Integer.parseInt(quantity)>0){
					cart.setCart_quantity(Integer.parseInt(quantity));
				}
				else{
					cart.setCart_quantity(p.getQuantity());
				}
			}catch(Exception e){
				setError(Cons.PRODUCT_QUANTITY_FIELD,e.getMessage());
			}
			
			float price=p.getPrice();
			Cart_attributes cart_attributes=null;
			List<Cart_attributes> list=new ArrayList<>();
			for(;attributes.hasMoreElements();){
				String option_id=attributes.nextElement();
				if(option_id.matches(s2)){
					String attribute_id=getValue(request,option_id);
					try{
						Validation.validateId(attribute_id);
						Product_attributes pa=pas.find(Integer.parseInt(attribute_id));
						cart_attributes=new Cart_attributes();
						cart_attributes.setClient(client);
						cart_attributes.setCart(cart);
						cart_attributes.setProduct_options_value(pa.getProduct_options_value());
						list.add(cart_attributes);
						price+=pa.getOptions_values_price();
					}catch(Exception e){
						setError(Cons.ATTRIBUTE_ID_FIELD,e.getMessage());
					}
				}
				
			}
			cart.setCart_attributes(list);
			cart.setFinal_price(price*cart.getCart_quantity());
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}	
		else
			result="false";
		return cart;
	}
	
	public Cart updateCart(HttpServletRequest request){
		String cart_id = getValue(request,Cons.CART_ID_FIELD);
		String quantity = getValue(request,Cons.PRODUCT_QUANTITY_FIELD);
		Cart cart=null;
		try{
			Validation.validateId(cart_id);
			cart=cs.findCart(Integer.parseInt(cart_id));
			Validation.validateQuantity(quantity);
			if(Integer.parseInt(quantity)<=cart.getProduct().getQuantity() && Integer.parseInt(quantity)>0){
				cart.setFinal_price((cart.getFinal_price()/cart.getCart_quantity())*Integer.parseInt(quantity));
				cart.setCart_quantity(Integer.parseInt(quantity));
			}
			else{
				cart.setFinal_price((cart.getFinal_price()/cart.getCart_quantity())*Integer.parseInt(quantity));
				cart.setCart_quantity(cart.getProduct().getQuantity());
			}
		}catch(Exception e){
			setError(Cons.CART_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty())
			result="true";
		else
			result="false";
		return cart;
	}
	
	public Cart deleteCart(HttpServletRequest request){
		String cart_id = getValue(request,Cons.CART_ID_FIELD);
		Cart cart=null;
		try{
			Validation.validateId(cart_id);
			cart=cs.findCart(Integer.parseInt(cart_id));
			
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}else{
			result="false";
		}
		return cart;
	}
}