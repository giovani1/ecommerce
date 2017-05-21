package com.ensa.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.ensa.models.Orders;
import com.ensa.models.OrderProducts;
import com.ensa.models.Order_product_attributes;
import com.ensa.models.Product;

/**
 * Session Bean implementation class Order_productService
 */
@Stateless
@LocalBean
public class Order_productService {
	@PersistenceContext( unitName = "ec1" )
	EntityManager em;
	
	public void addProductToOrder(Orders order, Product product,@NotNull int product_quantity){
		OrderProducts orderProducts = new OrderProducts();
		orderProducts.setOrder(order);
		orderProducts.setProduct(product);
		orderProducts.setProduct_quantity(product_quantity);
		orderProducts.setProduct_name(product.getImage());
		orderProducts.setProduct_price(product.getPrice());
		orderProducts.setFinal_price(product.getPrice());
		em.persist(orderProducts);
		em.flush();
	}
	public void addProductToOrder(OrderProducts orderProducts){
		em.persist(orderProducts);
		em.flush();
	}
	public void addOrderAttributesToProductOrder(Orders order,OrderProducts orderProducts,@NotNull String options,@NotNull String options_values,@NotNull float price){
		Order_product_attributes order_product_attributes=new Order_product_attributes();
		order_product_attributes.setOrder(order);
		order_product_attributes.setOptions(options);
		order_product_attributes.setOptions_values(options_values);
		order_product_attributes.setOrderproduct(orderProducts);
		order_product_attributes.setOptions_values_price(price);
		orderProducts.setFinal_price(orderProducts.getFinal_price()+price);
		em.merge(orderProducts);
		em.persist(order_product_attributes);
		em.flush();
	}
	public void addOrderAttributesToProductOrder(Order_product_attributes order_product_attributes){
		em.persist(order_product_attributes);
		em.flush();
	}
	public List<OrderProducts> findByProduct(@NotNull String productName) {
		TypedQuery<OrderProducts> typedQuery = em.createNamedQuery(OrderProducts.FIND_BY_PRODUCT_NAME, OrderProducts.class);
        typedQuery.setParameter("pname", productName);
        return typedQuery.getResultList();
    }
}
