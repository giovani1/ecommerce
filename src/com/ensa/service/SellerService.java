package com.ensa.service;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.ensa.models.Adresse;
import com.ensa.models.Product;
import com.ensa.models.Seller;

/**
 * Session Bean implementation class SellerService
 */
@Stateless
@LocalBean
public class SellerService {

	@PersistenceContext(unitName = "ec1")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public SellerService() {
		// TODO Auto-generated constructor stub
	}

	public Seller add(Seller seller) {
		em.persist(seller);
		return seller;
	}

	public List<Seller> getAllSellers() {
		TypedQuery<Seller> query = em.createNamedQuery(Seller.FIND_ALL, Seller.class);
		return (List<Seller>)query.getResultList();
	}

	public Seller getSeller(int sellerId) {
		return em.find(Seller.class, sellerId);
	}

	public Seller update(Seller seller) {
		em.merge(seller);
		return seller;
	}

	public void remove(Seller seller) {
		em.remove(seller);
	}
	
	public Seller getSeller(String email){
		Seller seller = null;
		Query query = em.createNamedQuery(Seller.FIND_BY_EMAIL, Seller.class);
		query.setParameter("email", email);

		try {

			seller = (Seller) query.getSingleResult();

		} catch (NoResultException e) {

			return null;
		}

		return seller;
	}

	public Seller getSeller(String email, String password) {

		Seller seller = null;
		Query query = em.createNamedQuery(Seller.FIND_BY_EMAIL_AND_PASSWORD, Seller.class);
		query.setParameter("email", email);
		query.setParameter("password", password);

		try {

			seller = (Seller) query.getSingleResult();
			seller.getInfo().setLastLoginDate(new Date());
			em.merge(seller);

		} catch (NoResultException e) {

			return null;
		}

		return seller;
	}

	public void addProductToSeller(int productId, int sellerId) {
		Product product = em.find(Product.class, productId);
		Seller seller = getSeller(sellerId);

		List<Product> lProduct = seller.getProducts();
		lProduct.add(product);
		seller.setProducts(lProduct);
		product.setSeller(seller);
	}

	public void addAdressToSeller(int adressId, int sellerId) {

		Seller seller = getSeller(sellerId);
		Adresse adress = em.find(Adresse.class, sellerId);

		List<Adresse> aList = seller.getPerson().getAdresses();
		aList.add(adress);
		seller.getPerson().setAdresses(aList);
		seller.getInfo().setLastModifiedDate(new Date());
		adress.setPerson(seller.getPerson());

	}


}
