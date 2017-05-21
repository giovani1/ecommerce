package com.ensa.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cart_attributes
 *
 */
@Entity

public class Cart_attributes implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="client_fk")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="cart_fk")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="product_options_value_fk")
	private Product_options_value product_options_value;
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * @return the product_options_value
	 */
	public Product_options_value getProduct_options_value() {
		return product_options_value;
	}

	/**
	 * @param product_options_value the product_options_value to set
	 */
	public void setProduct_options_value(Product_options_value product_options_value) {
		this.product_options_value = product_options_value;
	}

	

}
