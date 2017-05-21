package com.ensa.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Order_product_attributes implements Serializable {

	
	@Transient
	private static final long serialVersionUID = 1L;
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="order_fk")
	private Orders order;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_product_fk")
	private OrderProducts orderproduct;
	
	@Column
	@NotNull
	private String options;
	
	@Column
	@NotNull
	private String options_values;
	
	@Column
	@NotNull
	private float options_values_price;

	public Order_product_attributes() {
	}

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
	 * @return the order
	 */
	public Orders getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Orders order) {
		this.order = order;
	}

	/**
	 * @return the orderproduct
	 */
	public OrderProducts getOrderproduct() {
		return orderproduct;
	}

	/**
	 * @param orderproduct the orderproduct to set
	 */
	public void setOrderproduct(OrderProducts orderproduct) {
		this.orderproduct = orderproduct;
	}

	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(String options) {
		this.options = options;
	}

	/**
	 * @return the options_values
	 */
	public String getOptions_values() {
		return options_values;
	}

	/**
	 * @param options_values the options_values to set
	 */
	public void setOptions_values(String options_values) {
		this.options_values = options_values;
	}

	/**
	 * @return the options_values_price
	 */
	public float getOptions_values_price() {
		return options_values_price;
	}

	/**
	 * @param options_values_price the options_values_price to set
	 */
	public void setOptions_values_price(float options_values_price) {
		this.options_values_price = options_values_price;
	}
	
	
}
