package com.ensa.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = Adresse.FIND_BY_CITY, query = "SELECT a FROM Adresse a WHERE a.city = :city"),
    @NamedQuery(name = Adresse.FIND_BY_CLIENT_NAME, query = "SELECT a FROM Adresse a WHERE a.person.lastname =:lname AND a.person.firstname =:fname"),
    @NamedQuery(name = Adresse.FIND_ALL, query = "SELECT a FROM Adresse a")
})
public class Adresse implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	@NotNull
	private String adress;
	
	@Column
	@NotNull
	private String zipCode;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private City city;
	
	@ManyToOne
	@JoinColumn(name="person_fk")
	private Person person;
	
	public static final String FIND_BY_CITY = "Adresse.findByCity";
	public static final String FIND_BY_CLIENT_NAME = "Adresse.findByClientName";
	public static final String FIND_ALL = "Adresse.findAll";
	
	//constructor
	
	public Adresse() {
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
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the zipCode
	 */
	

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}




}
