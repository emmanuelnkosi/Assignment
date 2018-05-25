package com.emmanuel.managedbeans;

import java.io.Serializable;
import java.util.logging.Logger;

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
public class LoginView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5228753665155702206L;
	
	private static Logger log = Logger.getLogger(LoginView.class.getName());

	@Inject
	private UserEJB userEJB;

	private String username;
	private String password;
	private User user;

	
	
	public void validatePassword(ComponentSystemEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
		
		// get password
				UIInput uiInputPassword = (UIInput) components.findComponent("password");
				String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
				String passwordId = uiInputPassword.getClientId();

		// Let required="true" do its job.
		if (password.isEmpty() ) {
			return;
		}
		
		if (username == null) {
			FacesMessage msg = new FacesMessage("Please Enter Valid Username");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(passwordId, msg);
			facesContext.renderResponse();
		}
	
		
	}

	public String login()throws Exception {
		
		user = userEJB.findUserByUsername(username);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				if(user.getRole().equals("admin")) {
					log.info("The user" + user);
					context.getExternalContext().getSessionMap().put("username", user.getUsername());
					return "admin";
				}else {
					return "simplecalc";
				}
			}else {
				return "signin";
			}
			
		}else {
			context.addMessage(null, new FacesMessage("Unknown login, try again"));
			return "signin";
		}
			
			
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
	
	

	

}
