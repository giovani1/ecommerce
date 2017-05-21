package com.ensa.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@NamedQueries({
    @NamedQuery(name = Categorie.FIND_BY_NAME, query = "SELECT c FROM Categorie c WHERE c.name = :pname"),
    @NamedQuery(name = Categorie.FIND_ALL, query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = Categorie.FIND_By_PARENT, query = "SELECT c FROM Categorie c where c.parent.id=:pid")
})
public class Categorie implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	private String image;
	
	@JoinColumn(name="parent_fk")
	private Categorie parent;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date_added;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date last_modified;
	
	public static final String FIND_BY_NAME = "Categorie.findByName";
	public static final String FIND_ALL = "Categorie.findAll";

	public static final String FIND_By_PARENT = "Categorie.findByParent";
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
	 * @return the parent
	 */
	public Categorie getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Categorie parent) {
		this.parent = parent;
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
	

	
}
