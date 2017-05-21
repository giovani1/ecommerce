package com.ensa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@NamedQueries({
    @NamedQuery(name = Seller.FIND_BY_EMAIL, query = "SELECT s FROM Seller s WHERE s.email = :email"),
    @NamedQuery(name = Seller.FIND_ALL, query = "SELECT s FROM Seller s"),
    @NamedQuery(name = Seller.FIND_BY_EMAIL_AND_PASSWORD , query = "SELECT s FROM Seller s WHERE s.email = :email AND s.password = :password")
})

public class Seller implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	@JoinColumn(name = "info_fk")
	private SellerInfo info;
	
	@Column
	@Email
	private String email;

	@Column(length = 256, nullable = false)
	@NotNull
	@Size(min = 1, max = 256)
	private String password;
	
	@Column
	private String phone;
	
	private Boolean status;
	
	@Column
	private String seller_name;
	
	@OneToMany(mappedBy="seller")
	private List<Product> products;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	@JoinColumn(name="person_fk")
	private Person person;

	public static final String FIND_ALL = "Seller.findAll";
	public static final String FIND_BY_EMAIL = "Seller.findByEmail";
	public static final String FIND_BY_EMAIL_AND_PASSWORD = "Seller.findByEmailAndPassword";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SellerInfo getInfo() {
		return info;
	}

	public void setInfo(SellerInfo info) {
		this.info = info;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	

}
