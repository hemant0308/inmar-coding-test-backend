package com.inmar.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the sub_category database table.
 * 
 */
@Entity
@Table(name = "sub_category")
@NamedQuery(name = "SubCategory.findAll", query = "SELECT s FROM SubCategory s")
public class SubCategory extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "sub_category")
	private String subCategory;

	// bi-directional many-to-one association to Category
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_category_id")
	private Category category;

	public SubCategory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubCategory() {
		return this.subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}