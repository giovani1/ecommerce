package com.ensa.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Adresse;
import com.ensa.models.Cart;
import com.ensa.models.Cart_attributes;
import com.ensa.models.City;
import com.ensa.models.Client;
import com.ensa.models.Orders;
import com.ensa.models.Product;
import com.ensa.models.OrderProducts;
import com.ensa.models.Order_product_attributes;
import com.ensa.service.AdressService;
import com.ensa.service.CartService;
import com.ensa.service.ClientService;
import com.ensa.service.OrderService;
import com.ensa.util.Cons;
import com.ensa.util.Validation;

public class OrderForm extends Form{
	
	private OrderService os;
	private CartService cs;
	private AdressService as;
	public OrderForm(CartService cs, OrderService os,AdressService as) {
		this.os=os;
		this.cs=cs;
		this.as = as;
	}

	public Orders addOrder(HttpServletRequest request, Client client) {
		
		String delivery_name = getValue(request,Cons.DELIVERY_NAME_FIELD);
		String delivery_adress = getValue(request,Cons.DELIVERY_ADRESS_FIELD);
		Orders order=new Orders();
		List<Cart> carts=cs.findByClient(client.getId());
		
		try{
			Validation.validateName(delivery_name);
			System.out.println("------------ validateName");
		}catch(Exception e){
			setError(Cons.DELIVERY_NAME_FIELD,e.getMessage());
			System.out.println("------------ validateName ex");

		}
		order.setDelivery_name(delivery_name);
		
		try{
			
			Validation.validateId(delivery_adress);
			System.out.println(String.valueOf(Integer.parseInt(delivery_adress)));
			System.out.println("-id ");
			
			Adresse ad=as.getAdresse(Integer.parseInt(delivery_adress));
		
			System.out.println("-ar");
			order.setDelivery_adress(ad.getAdress());
			System.out.println("-addr");
			order.setDelivery_city(ad.getCity().name());
			System.out.println("-name");
			order.setDelivery_zipCode(ad.getZipCode());
			System.out.println("-zip");
			System.out.println("------------ validateAddress ");

		}catch(Exception e){
			setError(Cons.DELIVERY_ADRESS_FIELD,e.getMessage());
			System.out.println("------------ validateAddress ex");

			
		}

		order.setClient(client);
		order.setLast_modified(new Date());
		order.setDate_purchased(new Date());
		order.setStatus("en shipping");
		OrderProducts e=null;
		List<Cart_attributes> attributes=null;
		List<OrderProducts> list2=new ArrayList<OrderProducts>();
		for(int i=0;i<carts.size();i++){
			Cart cart=carts.get(i);
			e = new OrderProducts();
			e.setProduct(cart.getProduct());
			e.setProduct_name(cart.getProduct().getName());
			e.setProduct_price(cart.getProduct().getPrice());
			e.setFinal_price(cart.getFinal_price());
			e.setProduct_quantity(cart.getCart_quantity());
			Order_product_attributes ee=null;
			List<Order_product_attributes> list1=new ArrayList<Order_product_attributes>();
			attributes=cart.getCart_attributes();
			for(int j=0;j!=attributes.size();j++){
				ee=new Order_product_attributes();
				ee.setOptions(attributes.get(j).getProduct_options_value().getProduct_options().getName());
				ee.setOptions_values(attributes.get(j).getProduct_options_value().getName());
				ee.setOrder(order);
				ee.setOrderproduct(e);
				list1.add(ee);
			}
			e.setOrder_products_attributes(list1);
			
			e.setOrder(order);
			list2.add(e);
		}
		order.setOrder_products(list2);
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return order;
	}

	public Orders getOrder(HttpServletRequest request) {
		String id = request.getParameter("id");
		Orders order=null;
		try{
			Validation.validateId(id);
			order=os.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.ORDER_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return order;
	}

	public void confirmOrder(HttpServletRequest request) {
		String id = request.getParameter("id");
		Orders order=null;
		try{
			Validation.validateId(id);
			order=os.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.ORDER_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
			order.setStatus("delivred");
			order.setLast_modified(new Date());
			order.setDate_finished(new Date());
			os.update(order);
		}
		else
			result="false";
	}

	public Orders updateComment(HttpServletRequest request) {
		String id = request.getParameter(Cons.ORDER_ID_FIELD);
		String comment = request.getParameter("comment");
		Orders order=null;
		try{
			Validation.validateId(id);
			order=os.find(Integer.parseInt(id));
			try{
				Validation.validateText(comment);
				order.setComment(comment);
				order.setLast_modified(new Date());
			}catch(Exception e){
				setError(Cons.ORDER_ID_FIELD,e.getMessage());
			}
		}catch(Exception e){
			setError(Cons.ORDER_ID_FIELD,e.getMessage());
		}
		
		if(errors.isEmpty())
			result="true";
		else
			result="false";
		return null;
	}

}