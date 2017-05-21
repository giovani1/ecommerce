package com.ensa.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cart
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Cart.FIND_ALL, query 		= "SELECT c FROM Cart c"),
    @NamedQuery(name = Cart.FIND_BY_CLIENT, query	= "SELECT c FROM Cart c WHERE c.client.id=:cid")
})
public class Cart implements Serializable {

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
	@JoinColumn(name="product_fk")
	private Product product;
	
	@Column
	private int cart_quantity;
	
	@Column
	private float final_price;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date_added;
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL)
	private List<Cart_attributes> cart_attributes;
	
	public static final String FIND_ALL = "Cart.findAll";
	public static final String FIND_BY_CLIENT= "Product.findByClient";
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the cart_quantity
	 */
	public int getCart_quantity() {
		return cart_quantity;
	}

	/**
	 * @param cart_quantity the cart_quantity to set
	 */
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}

	/**
	 * @return the final_price
	 */
	public float getFinal_price() {
		return final_price;
	}

	/**
	 * @param final_price the final_price to set
	 */
	public void setFinal_price(float final_price) {
		this.final_price = final_price;
	}

	/**
	 * @return the date_added
	 */
	public Date getDate_added() {
		return date_added;
	}

	/**
	 * @param date_added the date_added to set
	 */
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}

	/**
	 * @return the cart_attributes
	 */
	public List<Cart_attributes> getCart_attributes() {
		return cart_attributes;
	}

	/**
	 * @param cart_attributes the cart_attributes to set
	 */
	public void setCart_attributes(List<Cart_attributes> cart_attributes) {
		this.cart_attributes = cart_attributes;
	}
}
