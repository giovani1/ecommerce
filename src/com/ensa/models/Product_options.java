package com.ensa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = Product_options.FIND_ALL, query = "SELECT p FROM Product_options p")
})
public class Product_options implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	@NotNull
	private String name;
	
	@OneToMany(mappedBy="product_options",cascade=CascadeType.ALL)
	private List<Product_options_value> product_options_value;
	
	public static final String FIND_ALL = "Product_options.findAll";

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
	 * @return the product_options_value
	 */
	public List<Product_options_value> getProduct_options_value() {
		return product_options_value;
	}

	/**
	 * @param product_options_value the product_options_value to set
	 */
	public void setProduct_options_value(List<Product_options_value> product_options_value) {
		this.product_options_value = product_options_value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product_options [id=" + id + ", name=" + name + ", product_options_value=" + product_options_value
				+ "]";
	}
	
	
   
}
