package com.ensa.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ensa.models.Product_options;
import com.ensa.models.Product_options_value;

/**
 * Session Bean implementation class Product_optionsService
 */
@Stateless
@LocalBean
public class Product_optionsService {
	@PersistenceContext( unitName = "ec1" )
	EntityManager em;

	public Product_options add(Product_options product_options){
		em.persist(product_options);
		em.flush();
		return product_options;
	}
	
	public Product_options update(Product_options product_options){
		return em.merge(product_options);
	}
	public Product_options find(int id){
		return em.find(Product_options.class, id);
	}
	public void remove(Product_options product_options){
		em.remove(em.merge(product_options));
		em.flush();
	}

	public Product_options_value addValue(Product_options_value product_options_value){
		em.persist(product_options_value);
		em.flush();
		return product_options_value;
	}
	
	public Product_options_value updateValue(Product_options_value product_options_value){
		return em.merge(product_options_value);
	}
	public Product_options_value findValue(int id){
		return em.find(Product_options_value.class, id);
	}
	public void removeValue(Product_options_value product_options_value){
		em.remove(em.merge(product_options_value));
	}
	
	public List<Product_options> findAll() {
		TypedQuery<Product_options> typedQuery = em.createNamedQuery(Product_options.FIND_ALL, Product_options.class);
        return typedQuery.getResultList();
	}
	
	public List<Product_options_value> findAllValue() {
		TypedQuery<Product_options_value> typedQuery = em.createNamedQuery(Product_options_value.FIND_ALL, Product_options_value.class);
        return typedQuery.getResultList();
	}
}
