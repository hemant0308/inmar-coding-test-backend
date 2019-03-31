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
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	// bi-directional many-to-one association to Department
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_department_id")
	private Department department;

	// bi-directional many-to-one association to SubCategory
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<SubCategory> subCategories;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<SubCategory> getSubCategories() {
		return this.subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public SubCategory addSubCategory(SubCategory subCategory) {
		getSubCategories().add(subCategory);
		subCategory.setCategory(this);

		return subCategory;
	}

	public SubCategory removeSubCategory(SubCategory subCategory) {
		getSubCategories().remove(subCategory);
		subCategory.setCategory(null);

		return subCategory;
	}

}