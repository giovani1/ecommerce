package com.ensa.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class ClientInfo implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer numbers_logons;
	
	@Column(name="registration_date")
	@Temporal(TemporalType.TIME)
	private Date registratioDate;
	
	@Column(name="last_login_date")
	@Temporal(TemporalType.TIME)
	private Date lastLoginDate;

	@Column(name="last_modified_date")
	@Temporal(TemporalType.TIME)
	private Date lastModifiedDate;

	@OneToOne(mappedBy = "info")
	private Client client;
	
	public int getId() {
		return id;
	}

	public Integer getNumbers_logons() {
		return numbers_logons;
	}

	public Date getRegistratioDate() {
		return registratioDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNumbers_logons(Integer numbers_logons) {
		this.numbers_logons = numbers_logons;
	}

	public void setRegistratioDate(Date registratioDate) {
		this.registratioDate = registratioDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
