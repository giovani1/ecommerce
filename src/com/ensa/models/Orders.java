package com.ensa.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
	@NamedQuery(name = Orders.FIND_BY_DELIVERY_NAME, query = "SELECT o FROM Orders o WHERE o.delivery_name = :dname"),
    @NamedQuery(name = Orders.FIND_ALL, query = "SELECT o FROM Orders o")
})
public class Orders implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String comment;
	
	@NotNull
	private String delivery_name;
	
	@NotNull
	private String delivery_adress;
	
	@NotNull
	private String delivery_zipCode;
	
	@NotNull
	private String delivery_city;
	
	@Temporal(TemporalType.TIME)
	private Date last_modified;
	
	@Temporal(TemporalType.TIME)
	private Date date_purchased;
	
	@Temporal(TemporalType.TIME)
	private Date date_finished;
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn(name="client_fk")
	private Client client;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderProducts> order_products;
	

	public static final String FIND_ALL = "Orders.findAll";
	public static final String FIND_BY_DELIVERY_NAME = "Orders.findByDeliveryName";
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the delivery_name
	 */
	public String getDelivery_name() {
		return delivery_name;
	}
	/**
	 * @param delivery_name the delivery_name to set
	 */
	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	/**
	 * @return the delivery_adress
	 */
	public String getDelivery_adress() {
		return delivery_adress;
	}
	/**
	 * @param delivery_adress the delivery_adress to set
	 */
	public void setDelivery_adress(String delivery_adress) {
		this.delivery_adress = delivery_adress;
	}
	/**
	 * @return the delivery_zipCode
	 */
	public String getDelivery_zipCode() {
		return delivery_zipCode;
	}
	/**
	 * @param delivery_zipCode the delivery_zipCode to set
	 */
	public void setDelivery_zipCode(String delivery_zipCode) {
		this.delivery_zipCode = delivery_zipCode;
	}
	/**
	 * @return the delivery_city
	 */
	public String getDelivery_city() {
		return delivery_city;
	}
	/**
	 * @param delivery_city the delivery_city to set
	 */
	public void setDelivery_city(String delivery_city) {
		this.delivery_city = delivery_city;
	}
	/**
	 * @return the last_modified
	 */
	public Date getLast_modified() {
		return last_modified;
	}
	
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	/**
	 * @return the date_purchased
	 */
	public Date getDate_purchased() {
		return date_purchased;
	}
	/**
	 * @param date_purchased the date_purchased to set
	 */
	public void setDate_purchased(Date date_purchased) {
		this.date_purchased = date_purchased;
	}
	/**
	 * @return the date_finished
	 */
	public Date getDate_finished() {
		return date_finished;
	}
	/**
	 * @param date_finished the date_finished to set
	 */
	public void setDate_finished(Date date_finished) {
		this.date_finished = date_finished;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the order_products
	 */
	public List<OrderProducts> getOrder_products() {
		return order_products;
	}
	/**
	 * @param order_products the order_products to set
	 */
	public void setOrder_products(List<OrderProducts> order_products) {
		this.order_products = order_products;
	}
	
	
}
