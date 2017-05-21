package com.ensa.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ensa.models.Client;
import com.ensa.models.Product;
import com.ensa.models.Review;

/**
 * Session Bean implementation class ReviewService
 */
@Stateless
@LocalBean
public class ReviewService {
    
	@PersistenceContext(unitName = "ec1")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ReviewService() {
		// TODO Auto-generated constructor stub
	}

	public Review add(Review review) {
		em.persist(review);
		return review;
	}

	public Client addReviewToClient(Review review,int idClient){
		em.persist(review);
		Client client = em.find(Client.class, idClient);
		
		List<Review> rList = client.getReviews();
		rList.add(review);
		client.setReviews(rList);
		review.setClient(client);
		
		return client;
		
	}
	public Review addReviewToProduct(int reviewId,int productId){
		Product product = em.find(Product.class, productId);
		Review review = em.find(Review.class, reviewId);
		
		List<Review> rList = product.getReviews();
		rList.add(review);
		product.setReviews(rList);
		review.setProduct(product);;
		
		return review;
				
	}
	
	public Review getReviewId(int reviewId) {
		return em.find(Review.class, reviewId);
	}

	public void remove(Review review) {
		em.remove(review);
	}

	public Review update(Review review) {
		em.merge(review);
		return review;
	}

}
