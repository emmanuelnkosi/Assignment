package com.emmanuel.managedbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import com.emmanuel.ejb.UserEJB;
import com.emmanuel.entity.User;

@ManagedBean
@SessionScoped
public class RegisterView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4370768605073722938L;
	
	@Inject
	private UserEJB userEJB;
	
	private String username;
	private String password;
	private String confirmPassword;
	private String role;
	
	public void validatePassword(ComponentSystemEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
		
		   // get password
				UIInput uiInputPassword = (UIInput) components.findComponent("password");
				String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
				String passwordId = uiInputPassword.getClientId();

				// get confirm password
				UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
				String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
						: uiInputConfirmPassword.getLocalValue().toString();

				// Let required="true" do its job.
				if (password.isEmpty() || confirmPassword.isEmpty()) {
					return;
				}

				if (!password.equals(confirmPassword)) {
					FacesMessage msg = new FacesMessage("Confirm password does not match password");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					facesContext.addMessage(passwordId, msg);
					facesContext.renderResponse();
				}

				if (userEJB.findUserByUsername(username) != null) {
					FacesMessage msg = new FacesMessage("User with this e-mail already exists");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					facesContext.addMessage(passwordId, msg);
					facesContext.renderResponse();
				}
	}
	
	public String register() {
		User user = new User(username, password, role);
		userEJB.createUser(user);
	//	Logger.info("New user created with e-mail: " + username + " and password: " + password);

		return "regdone";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	


}
