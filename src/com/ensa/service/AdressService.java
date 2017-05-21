package com.ensa.service;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ensa.models.Adresse;
import com.ensa.models.Client;
import com.ensa.models.Seller;

/**
 * Session Bean implementation class AdressService
 */
@Stateless
@LocalBean
public class AdressService {
	
	@PersistenceContext(unitName = "ec1")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public AdressService() {
        // TODO Auto-generated constructor stub
    }

    public Adresse getAdresse(int id){
    	return em.find(Adresse.class,id);
    }
    
    public Adresse add(Adresse adress){
    	em.persist(adress);
    	return adress;
    }
    
    public void remove(Adresse adress){
    	em.remove(adress);
    }
    
    @SuppressWarnings("unchecked")
	public List<Adresse> getAllAdress(int personId){
    	List<Adresse> adList = null;
    	
    	Query query = em.createQuery("SELECT a FROM Adresse a WHERE person_fk = :person");
    	query.setParameter("person", personId);
    	
    	try{
    		
    		adList = (List<Adresse>) query.getResultList();
    	
    	}catch(NoResultException e ) {
            
    		return null;
        }
    	
    	return adList;
    }

    public Client AddAdressToClient(Adresse adress, int clientId) {
    	em.persist(adress);
		Client client = em.find(Client.class, clientId);

		List<Adresse> aList = client.getPerson().getAdresses();
		aList.add(adress);
		client.getPerson().setAdresses(aList);
		adress.setPerson(client.getPerson());
		client.getInfo().setLastModifiedDate(new Date());
		
		return client;
		
	}
    
    public Seller AddAdressToSeller(Adresse adress, int sellerId) {
    	em.persist(adress);
		Seller seller = em.find(Seller.class, sellerId);

		List<Adresse> aList = seller.getPerson().getAdresses();
		aList.add(adress);
		seller.getPerson().setAdresses(aList);
		adress.setPerson(seller.getPerson());
		seller.getInfo().setLastModifiedDate(new Date());
		
		em.flush();
		return seller;
		
	}
    
    public Adresse update(Adresse adress){
    	em.merge(adress);
    	return adress;
    }
}
