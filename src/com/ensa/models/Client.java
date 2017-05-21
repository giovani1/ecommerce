package com.ensa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@NamedQueries({
    @NamedQuery(name = Client.FIND_BY_EMAIL, query = "SELECT c FROM Client c WHERE c.email = :email"),
    @NamedQuery(name = Client.FIND_BY_NAME, query = "SELECT c FROM Client c WHERE c.person.lastname = :lname AND c.person.firstname = :fname"),
    @NamedQuery(name = Client.FIND_ALL, query = "SELECT c FROM Client c"),
    @NamedQuery(name = Client.FIND_BY_EMAIL_AND_PASSWORD, query = "SELECT c FROM Client c WHERE c.email = :email AND c.password = :password")
})

public class Client implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	@JoinColumn(name = "info_fk")
	private ClientInfo info;
	
	@Column(unique=true)
	@Email
	private String email;
	
	@Column(length = 256)
	@NotNull
	@Size(min = 1, max = 256)
	private String password;
	
	@Column
	private String phone;
	
	private Boolean status;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="client")
	private List<Review> reviews;
	
	@OneToMany(mappedBy="client")
	private List<Orders> orders;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="person_fk")
	private Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientInfo getInfo() {
		return info;
	}

	public void setInfo(ClientInfo info) {
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
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



	public static final String FIND_ALL = "Client.findAll";
	public static final String FIND_BY_EMAIL = "Client.findByEmail";
	public static final String FIND_BY_NAME = "Client.findByName";
	public static final String FIND_BY_EMAIL_AND_PASSWORD = "Client.findByEmailAndPassword";


	

	
}
