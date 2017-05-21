package com.ensa.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Product_attributes implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="product_fk")
	private Product product;
	
	
	@ManyToOne
	@JoinColumn(name="product_options_value_fk")
	private Product_options_value product_options_value;
	

	@Column
	@NotNull
	private float options_values_price;
	
	
	public Product_attributes() {
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product_attributes [id=" + id + ", product_options_value="
				+ product_options_value.getName() + ", options_values_price=" + options_values_price + "]";
	}
   
}
