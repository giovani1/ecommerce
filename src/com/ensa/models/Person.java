package com.ensa.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Person implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@NotNull

	private String firstname;
	
	@NotNull
	
	private String lastname;
	
	
	@OneToOne(mappedBy="person")
	private Client client;
	
	@OneToOne(mappedBy="person")
	private Seller seller;
	

	@Column
	@Past
	@Temporal(TemporalType.DATE)
	private Date birth;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	@OneToMany(mappedBy="person")
	private List<Adresse> adresses;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the adresses
	 */
	public List<Adresse> getAdresses() {
		return adresses;
	}

	/**
	 * @param adresses the adresses to set
	 */
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", birth=" + birth
				+ ", gender=" + gender + ", adresses=" + adresses + "]";
	}
	
}
