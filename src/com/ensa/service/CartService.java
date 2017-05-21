package com.ensa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.ensa.models.Cart;
import com.ensa.models.Cart_attributes;

/**
 * Session Bean implementation class CartService
 */
@Stateless
public class CartService  {

	@PersistenceContext( unitName = "ec1" )
    EntityManager em;
	
	public Cart addCart(@NotNull Cart cart){
		em.persist(cart);
		em.flush();
		return cart;
    }
	
	public Cart_attributes addAttributes(Cart_attributes cart_at){
		em.persist(cart_at);
		em.flush();
    	return cart_at;
    }
	public Cart findCart(@NotNull int id) {
        return em.find(Cart.class, id);
    }
	public Cart_attributes findAttributes(@NotNull int id) {
        return em.find(Cart_attributes.class, id);
    }
	
	public void updateCart(@NotNull Cart cart){
		em.merge(cart);
    }
	public Cart_attributes updateAttributes(Cart_attributes cart_at){
		return em.merge(cart_at);
    }
	
    public void removeCart(@NotNull Cart cart){
    	em.remove(em.merge(cart));
    }
    public void removeAttributes(Cart_attributes cart_at){
    	em.remove(em.merge(cart_at));
    }
	public List<Cart> findAll(){
		TypedQuery<Cart> typedQuery = em.createNamedQuery(Cart.FIND_ALL, Cart.class);
        return typedQuery.getResultList();
	}

	public List<Cart> findByClient(@NotNull int id) {
		TypedQuery<Cart> typedQuery = em.createNamedQuery(Cart.FIND_BY_CLIENT, Cart.class);
		typedQuery.setParameter("cid", id);
        return typedQuery.getResultList();
	}

}
