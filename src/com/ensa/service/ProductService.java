package com.ensa.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.ensa.models.Product;


@Stateless
@LocalBean
public class ProductService {

	@PersistenceContext( unitName = "ec1" )
	EntityManager em;
	
    public Product add(@NotNull Product product){
    	em.persist(product);
    	em.flush();
    	return product;
    }
    
    public Product find(@NotNull int id){
    	return em.find(Product.class, id);
    }
    public Product find(int productId,Boolean x) {

		Product product =em.find(Product.class, productId);
    	if(x){
    		return product;
    	}
		product.setProduct_viewed(product.getProduct_viewed()+1);
		return em.merge(product);
	}
    public Product find(@NotNull String name) {
    	TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_NAME, Product.class);
        typedQuery.setParameter("pname", name);
        return typedQuery.getSingleResult();
    }
    
    public List<Product> findBySeller(@NotNull int id_seller) {
    	TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_SELLER, Product.class);
		typedQuery.setParameter("sid", id_seller);
	    return typedQuery.getResultList();
    }
    
    public List<Product> findBySeller(@NotNull int id_seller,boolean x) {
    	TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_SELLER_V, Product.class);
		typedQuery.setParameter("sid", id_seller);
		typedQuery.setParameter("status", true);
	    return typedQuery.getResultList();
    }
    
    public List<Product> findByCategory(@NotNull int id_categorie,boolean x){
		TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_CATEGORIE_V, Product.class);
		typedQuery.setParameter("cid", id_categorie);
		typedQuery.setParameter("status", true);
		return typedQuery.getResultList();
	}
    public List<Product> findByCategory(@NotNull int id_categorie){
		TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_CATEGORIE, Product.class);
		typedQuery.setParameter("cid", id_categorie);
		return typedQuery.getResultList();
	}
    public List<Product> findAll() {
    	TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_ALL, Product.class);
	    return typedQuery.getResultList();
    }
    public List<Product> findAll(boolean x) {
    	TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_ALL_V, Product.class);
    	typedQuery.setParameter("status", true);
	    return typedQuery.getResultList();
    }
    public Product update(Product product){
    	return em.merge(product);
    }
    public void remove(Product product){
    	em.remove(em.merge(product));
    }
	
}
