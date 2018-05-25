package com.emmanuel.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_groups")
public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6424643273534570505L;
	
	public static final String USERS_GROUP = "users";
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	private int id;
	
	@Column(name="username", nullable=false, length=255)
	private String username;
	
	
	@Column(name="groupname", nullable=false, length=32)
	private String groupname;
	
	public Group() {}

	public Group(String username, String groupname) {
		this.username = username;
		this.groupname = groupname;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	

	
	

}
