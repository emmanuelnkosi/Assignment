package com.emmanuel.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "User.findByUsername", 
			query = "SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name = "User.findByPassword", 
	query = "SELECT u FROM User u WHERE u.password = :password"),
	@NamedQuery(name = "User.findUser", 
	query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password")
})
@Table(name="user")
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	private int id;
	
	@Column(name="username", nullable=false, length=255)
	private String username;
	
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	@Column(name="role", nullable=false, length=30)
	private String role;
	
	public User() {}
	

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
