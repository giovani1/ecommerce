package com.ensa.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Review implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "product_fk")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "client_fk")
	private Client client;

	@Column
	private String review_text;

	@Column
	private Float review_rating;

	@Temporal(TemporalType.TIME)
	private Date date_added;

	@Temporal(TemporalType.TIME)
	private Date last_modified;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the review_text
	 */
	public String getText() {
		return review_text;
	}

	/**
	 * @param review_text
	 *            the review_text to set
	 */
	public void setText(String review_text) {
		this.review_text = review_text;
	}

	

	public Float getRating() {
		return review_rating;
	}

	public void setRating(Float review_rating) {
		this.review_rating = review_rating;
	}

	/**
	 * @return the date_added
	 */
	public Date getDate_added() {
		return date_added;
	}

	/**
	 * @param date_added
	 *            the date_added to set
	 */
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}

	/**
	 * @return the last_modified
	 */
	public Date getLast_modified() {
		return last_modified;
	}

	/**
	 * @param last_modified
	 *            the last_modified to set
	 */
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}


	

}
