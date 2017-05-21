package com.ensa.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import com.ensa.models.Product;
import com.ensa.models.Product_attributes;
import com.ensa.models.Product_options_value;

/**
 * Session Bean implementation class Product_attributesService
 */
@Stateless
@LocalBean
public class Product_attributesService {
	
	@PersistenceContext( unitName = "ec1" )
	EntityManager em;

	public Product_attributes addAttributesToProducts(Product product, Product_options_value product_options_value,float price){
		Product_attributes product_attributes=new Product_attributes();
		product_attributes.setProduct(product);
		product_attributes.setProduct_options_value(product_options_value);
		product_attributes.setOptions_values_price(price);
		
		em.persist(product_attributes);
		em.flush();
		return product_attributes;
	}
	public Product_attributes add(Product_attributes product_attributes){
		em.persist(product_attributes);
		em.flush();
		return product_attributes;
	}
	
	public Product_attributes find(@NotNull int id){
		return em.find(Product_attributes.class,id);
	}
	public Product_attributes update(Product_attributes product_attributes){
		Product_attributes pa=em.merge(product_attributes);
		em.flush();
		return pa;
	}
	public void remove(Product_attributes product_attributes){
		em.remove(em.merge(product_attributes));
		em.flush();
	}
}
