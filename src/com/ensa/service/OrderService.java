package com.ensa.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.ensa.models.OrderProducts;
import com.ensa.models.Orders;
import com.ensa.models.Product;

@Stateless
@LocalBean
public class OrderService {
	
	@PersistenceContext( unitName = "ec1" )
    EntityManager em;
    
    public Orders add(@NotNull Orders order){
    	em.persist(order);
    	em.flush();
    	return order;
    }
    
    public Orders find(@NotNull int id) {
        return em.find(Orders.class, id);
    }
    public List<Orders> findByClient(int id){
    	TypedQuery<Orders> typedQuery = em.createNamedQuery(Orders.FIND_BY_CLIENT, Orders.class);
    	typedQuery.setParameter("cid", id);
        return typedQuery.getResultList();
    }
    
    public List<OrderProducts> findBySeller(int id){
    	TypedQuery<OrderProducts> typedQuery = em.createNamedQuery(OrderProducts.FIND_BY_SELLER, OrderProducts.class);
    	typedQuery.setParameter("sid", id);
    	typedQuery.setParameter("status", "true");
        return typedQuery.getResultList();
    }
    
    public List<Orders> findAll() {
        TypedQuery<Orders> typedQuery = em.createNamedQuery(Orders.FIND_ALL, Orders.class);
        return typedQuery.getResultList();
    }
    
    public void update(@NotNull Orders order){
    	em.merge(order);
    }
    public void remove(@NotNull Orders order){
    	em.remove(order);
    }

}
