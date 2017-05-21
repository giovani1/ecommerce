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
import com.ensa.models.Client;
import com.ensa.models.Orders;
import com.ensa.models.Review;

@Stateless
@LocalBean
public class ClientService {

	@PersistenceContext(unitName = "ec1")
	EntityManager em;

	public ClientService() {
	}

	public Client add( Client client) {
		
		em.persist(client);
		return client;
	}

	public List<Client> getAllClients() {
		
		TypedQuery<Client> query = em.createNamedQuery(Client.FIND_ALL, Client.class);
		return (List<Client>)query.getResultList();
		
	}

	public Client update(Client client) {
		em.merge(client);
		return client;
	}

	public void remove(Client client) {
		em.remove(client);
	}

	public Client getClient(String email, String password) {

		Client client = null;
		Query query = em.createNamedQuery(Client.FIND_BY_EMAIL_AND_PASSWORD, Client.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		try {

			client = (Client) query.getSingleResult();
			client.getInfo().setLastLoginDate(new Date());
			client.getInfo().setLastLoginDate(new Date());
			em.merge(client);

		} catch (NoResultException e) {

			return null;
		}

		return client;
	}

	public Client getClient(String email) {

		Client client = null;
		Query query = em.createNamedQuery(Client.FIND_BY_EMAIL, Client.class);
		query.setParameter("email", email);

		try {

			client = (Client) query.getSingleResult();

		} catch (NoResultException e) {

			return null;
		}

		return client;
	}

	public Client getClientById(int id) {
		
		return em.find(Client.class, id);
		
	}

	public void AddReviewToClient(int reviewId, int clientId) {

		Client client = getClientById(clientId);
		Review review = em.find(Review.class, reviewId);
		review.setLast_modified(new Date());
		List<Review> lReview = client.getReviews();
		lReview.add(review);
		client.setReviews(lReview);
		review.setClient(client);

	}

	public void AddOrderToClient(int orderId, int clientId) {

		Client client = getClientById(clientId);
		Orders order = em.find(Orders.class, orderId);

		List<Orders> lReview = client.getOrders();
		lReview.add(order);
		client.setOrders(lReview);
		order.setClient(client);

	}

	


}
