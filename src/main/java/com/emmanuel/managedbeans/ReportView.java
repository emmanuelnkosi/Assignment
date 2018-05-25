package com.emmanuel.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.emmanuel.ejb.UserEJB;
import com.emmanuel.entity.Compute;

@ManagedBean
@SessionScoped
public class ReportView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181421136743916545L;

	private static Logger log = Logger.getLogger(ReportView.class.getName());
	
	private int id;
	private String request;
	private String username;
	private Date timeCaptured;
	private Compute compute;
	private int answer;
	
	@Inject
	private UserEJB userEJB;
	
	public List<Compute> getAllOperations() {
		 
	return userEJB.findAll();
	}
	
	

	public int getAnswer() {
		return answer;
	}



	public void setAnswer(int answer) {
		this.answer = answer;
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



	public Compute getCompute() {
		return compute;
	}

	public void setCompute(Compute compute) {
		this.compute = compute;
	}
	
	

}
