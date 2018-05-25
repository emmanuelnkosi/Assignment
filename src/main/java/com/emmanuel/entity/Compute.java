package com.emmanuel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Compute.findAll", 
			query = "SELECT r FROM Compute r"),
	@NamedQuery(name = "Compute.findComputeByUsername", 
	query = "SELECT r FROM Compute r where r.username = :username")
})
@Table(name="compute")
public class Compute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3263597905592369566L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	private int id;
	@Column(name="request", nullable=false, length=60)
	private String request;
	@Column(name="answer", nullable=false, length=60)
	private int answer;
	@Column(name="username", nullable=false, length=60)
	private String username;

	@Column(name="timeCaptured", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeCaptured;
	
	public Compute() {}
	
	public Compute(String request, int answer, String username, Date date) {
		super();
		this.request = request;
		this.answer = answer;
		this.username = username;
		this.timeCaptured = date;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTimeCaptured() {
		return timeCaptured;
	}

	public void setTimeCaptured(Date timeCaptured) {
		this.timeCaptured = timeCaptured;
	}

	
	

}
