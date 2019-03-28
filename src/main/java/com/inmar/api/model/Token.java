package com.inmar.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(name = "token")
@NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
public class Token extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "destroyed_at")
	private Date destroyedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "recent_active")
	private Date recentActive;

	private String token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user_id")
	private User user;

	public Token() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDestroyedAt() {
		return this.destroyedAt;
	}

	public void setDestroyedAt(Date destroyedAt) {
		this.destroyedAt = destroyedAt;
	}

	public Date getRecentActive() {
		return this.recentActive;
	}

	public void setRecentActive(Date recentActive) {
		this.recentActive = recentActive;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}