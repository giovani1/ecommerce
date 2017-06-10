package com.ensa.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Cart;
import com.ensa.models.Cart_attributes;
import com.ensa.models.Client;
import com.ensa.models.Orders;
import com.ensa.models.Product;
import com.ensa.models.OrderProducts;
import com.ensa.models.Order_product_attributes;
import com.ensa.service.CartService;
import com.ensa.service.OrderService;
import com.ensa.util.Cons;
import com.ensa.util.Validation;

public class OrderForm extends Form{
	
	private OrderService os;
	private CartService cs;
	
	public OrderForm(CartService cs, OrderService os) {
		this.os=os;
		this.cs=cs;
	}

	public Orders addOrder(HttpServletRequest request, Client client) {
		String delivery_name = getValue(request,Cons.DELIVERY_NAME_FIELD);
		String delivery_adress = getValue(request,Cons.DELIVERY_ADRESS_FIELD);
		String delivery_zipCode = getValue(request,Cons.DELIVERY_ZIPCODE_FIELD);
		String delivery_city = getValue(request,Cons.DELIVERY_CITY_FIELD);
		//String cart_id = getValue(request,CART_ID_FIELD);
		Orders order=new Orders();
		//Map<Integer,Cart> carts=(HashMap<Integer,Cart>) request.getServletContext().getAttribute("cart");
		//if(carts.isEmpty() || carts.equals(null)) return null;
		List<Cart> carts=new ArrayList<Cart>();
		
		try{
			Validation.validateName(delivery_name);
		}catch(Exception e){
			setError(Cons.DELIVERY_NAME_FIELD,e.getMessage());
		}
		order.setDelivery_name(delivery_name);
		
		try{
			Validation.validateText(delivery_adress);
		}catch(Exception e){
			setError(Cons.DELIVERY_ADRESS_FIELD,e.getMessage());
		}
		order.setDelivery_adress(delivery_adress);
		
		try{
			Validation.validateZipCode(delivery_zipCode);
		}catch(Exception e){
			setError(Cons.DELIVERY_ZIPCODE_FIELD,e.getMessage());
		}
		order.setDelivery_zipCode(delivery_zipCode);
		
		try{
			Validation.validateCity(delivery_city);
		}catch(Exception e){
			setError(Cons.DELIVERY_CITY_FIELD,e.getMessage());
		}
		order.setDelivery_city(delivery_city);
		order.setClient(client);
		order.setLast_modified(new Date());
		order.setDate_purchased(new Date());
		order.setStatus("shipping");
		OrderProducts e=null;
		List<Cart_attributes> attributes=null;
		List<OrderProducts> list2=null;
		for(int i=0;i<carts.size();i++){
									//cart			=				orderproducts
			Cart cart=carts.get(i);
			e = new OrderProducts();
			e.setOrder(order);
			e.setProduct(cart.getProduct());
			e.setProduct_name(cart.getProduct().getName());
			e.setProduct_price(cart.getProduct().getPrice());
			e.setFinal_price(cart.getFinal_price());
			e.setProduct_quantity(cart.getCart_quantity());
			Order_product_attributes ee=null;
			List<Order_product_attributes> list1=null;
			attributes=cart.getCart_attributes();
			for(int j=0;j!=attributes.size();j++){
				ee=new Order_product_attributes();
				ee.setOptions(attributes.get(j).getProduct_options_value().getProduct_options().getName());
				ee.setOptions_values(attributes.get(j).getProduct_options_value().getName());
				ee.setOrder(order);
				ee.setOrderproduct(e);
				list1=e.getOrder_products_attributes();
				list1.add(ee);
				e.setOrder_products_attributes(list1);
			}
			e.setOrder(order);
			list2=order.getOrder_products();
			list2.add(e);
			order.setOrder_products(list2);
		}
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return order;
	}

	public Orders getOrder(HttpServletRequest request) {
		String id = request.getQueryString();
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

	public Orders confirmOrder(HttpServletRequest request) {
		String id = request.getParameter("orderid");
		Orders order=null;
		try{
			Validation.validateId(id);
			order=os.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.ORDER_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
			List<OrderProducts> s=order.getOrder_products();
			for(int i=0;i!=s.size();i++){
				Product p=s.get(i).getProduct();
				p.setQuantity(p.getQuantity()-s.get(i).getProduct_quantity());
			}
			order.setStatus("delivred");
			order.setDate_finished(new Date());
			os.update(order);
		}
		else
			result="false";
		return order;
	}

}
