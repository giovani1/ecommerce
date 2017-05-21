package com.ensa.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Product_options_value
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Product_options_value.FIND_ALL, query = "SELECT p FROM Product_options_value p")
})
public class Product_options_value implements Serializable {

	public static final String FIND_ALL = "Product_options_value.findAll";
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	@NotNull
	private String name;
	
	@ManyToOne
	@JoinColumn(name="product_options_fk")
	private Product_options product_options;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the product_options
	 */
	public Product_options getProduct_options() {
		return product_options;
	}

	/**
	 * @param product_options the product_options to set
	 */
	public void setProduct_options(Product_options product_options) {
		this.product_options = product_options;
	}
	
   
}
