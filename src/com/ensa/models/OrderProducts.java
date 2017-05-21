package com.ensa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = OrderProducts.FIND_BY_PRODUCT_NAME, query = "SELECT o FROM OrderProducts o WHERE o.product.name = :pname")
})
public class OrderProducts implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="order_fk")
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name="product_fk")
	private Product product;
	
	@Column
	@NotNull
	private String product_name;
	
	@Column
	@NotNull
	private float product_price;
	
	@Column
	@NotNull
	private float final_price;

	@Column
	@NotNull
	private Integer product_quantity;
	
	@OneToMany(mappedBy="orderproduct" ,cascade=CascadeType.ALL)
	private List<Order_product_attributes> order_products_attributes;
	
	public static final String FIND_BY_PRODUCT_NAME= "Order.findByProductName";
	
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
	 * @return the order_products_attributes
	 */
	public List<Order_product_attributes> getOrder_products_attributes() {
		return order_products_attributes;
	}

	/**
	 * @param order_products_attributes the order_products_attributes to set
	 */
	public void setOrder_products_attributes(List<Order_product_attributes> order_products_attributes) {
		this.order_products_attributes = order_products_attributes;
	}

	/**
	 * @return the product_quantity
	 */
	public Integer getProduct_quantity() {
		return product_quantity;
	}

	/**
	 * @param product_quantity the product_quantity to set
	 */
	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}

	/**
	 * @return the product_name
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * @param product_name the product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	/**
	 * @return the product_price
	 */
	public float getProduct_price() {
		return product_price;
	}

	/**
	 * @param product_price the product_price to set
	 */
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
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
}
