package com.ensa.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name = Product.FIND_BY_NAME, query 			= "SELECT p FROM Product p WHERE p.name = :pname"),
	@NamedQuery(name = Product.FIND_BY_URL, query 			= "SELECT p FROM Product p WHERE p.url = :purl"),
    @NamedQuery(name = Product.FIND_ALL, query 				= "SELECT p FROM Product p"),
    @NamedQuery(name = Product.FIND_ALL_V, query 			= "SELECT p FROM Product p WHERE p.status = :status"),
    @NamedQuery(name = Product.FIND_BY_SELLER, query 		= "SELECT p FROM Product p WHERE p.seller.id=:sid"),
    @NamedQuery(name = Product.FIND_BY_SELLER_V, query 		= "SELECT p FROM Product p WHERE p.seller.id =:sid AND p.status = :status"),
	@NamedQuery(name = Product.FIND_BY_CATEGORIE, query 	= "SELECT p FROM Product p WHERE p.categorie.id=:cid"),
	@NamedQuery(name = Product.FIND_BY_CATEGORIE_V, query 	= "SELECT p FROM Product p WHERE p.categorie.id=:cid AND p.status = :status")
})

public class Product implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(length = 30, nullable = false)
	@NotNull
	private String name;
	
	@Column(length = 3000, nullable = false)
	@NotNull
	@Size(max = 3000)
	private String description;
	
	@Column
	private String url;

	@Column
	private Integer product_viewed;
	
	@Column
	private Integer quantity;
	
	@Column
	private String image;
	
	@Column
	private float price;
	
	@Column
	private boolean status;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date_added;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date last_modified;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date_available;
	
	@ManyToOne
	@JoinColumn(name="seller_fk")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name="categorie_fk")
	private Categorie categorie;
	
	@OneToMany(mappedBy="product")
	private List<Review> reviews;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<Product_attributes> products_attributes;

	
	public static final String FIND_BY_NAME = "Product.findByName";
	public static final String FIND_BY_URL = "Product.findByUrl";
	public static final String FIND_ALL = "Product.findAll";
	public static final String FIND_ALL_V = "Product.findAllvisible";
	public static final String FIND_BY_SELLER = "Product.findBySeller";
	public static final String FIND_BY_SELLER_V = "Product.findBySellervisible";
	public static final String FIND_BY_CATEGORIE = "Product.findByCategorie";
	public static final String FIND_BY_CATEGORIE_V = "Product.findByCategorievisible";
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the product_viewed
	 */
	public Integer getProduct_viewed() {
		return product_viewed;
	}
	/**
	 * @param product_viewed the product_viewed to set
	 */
	public void setProduct_viewed(Integer product_viewed) {
		this.product_viewed = product_viewed;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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
	 * @return the last_modified
	 */
	public Date getLast_modified() {
		return last_modified;
	}
	/**
	 * @param last_modified the last_modified to set
	 */
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	/**
	 * @return the date_available
	 */
	public Date getDate_available() {
		return date_available;
	}
	/**
	 * @param date_available the date_available to set
	 */
	public void setDate_available(Date date_available) {
		this.date_available = date_available;
	}
	/**
	 * @return the seller
	 */
	public Seller getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}
	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	/**
	 * @return the products_attributes
	 */
	public List<Product_attributes> getProducts_attributes(){
		return products_attributes;
	}
	/**
	 * @param products_attributes the products_attributes to set
	 */
	public void setProducts_attributes(List<Product_attributes> products_attributes) {
		this.products_attributes = products_attributes;
	}
	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
}
