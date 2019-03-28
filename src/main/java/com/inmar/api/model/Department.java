package com.inmar.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@Table(name = "department")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String department;

	// bi-directional many-to-one association to Location
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_location_id")
	private Location location;

	// bi-directional many-to-one association to Category
	@JsonIgnore
	@OneToMany(mappedBy = "department")
	private List<Category> categories;

	public Department() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setDepartment(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setDepartment(null);

		return category;
	}

}