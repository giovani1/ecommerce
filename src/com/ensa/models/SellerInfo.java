package com.ensa.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class SellerInfo implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String seller_url;
	
	@Column
	private Integer url_clicked;
	
	@Column(name="registration_date")
	@Temporal(TemporalType.TIME)
	private Date registrationDate;
	
	@Column(name="last_click_date")
	@Temporal(TemporalType.TIME)
	private Date lastLoginDate;

	@Column(name="last_modified_date")
	@Temporal(TemporalType.TIME)
	private Date lastModifiedDate;
	
	@OneToOne(mappedBy="info")
	private Seller seller;

	public int getId() {
		return id;
	}

	public String getSeller_url() {
		return seller_url;
	}

	public Integer getUrl_clicked() {
		return url_clicked;
	}

	public Date getRegistrationDate() {
		return registrationDate;
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

	public void setSeller_url(String seller_url) {
		this.seller_url = seller_url;
	}

	public void setUrl_clicked(Integer url_clicked) {
		this.url_clicked = url_clicked;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
}
