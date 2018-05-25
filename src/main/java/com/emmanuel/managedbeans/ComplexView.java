package com.emmanuel.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import org.mariuszgromada.math.mxparser.*;

import com.emmanuel.ejb.UserEJB;
import com.emmanuel.entity.Compute;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class ComplexView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2405830313539931163L;
	
	private static Logger log = Logger.getLogger(ComplexView.class.getName());
	
   private String equation;
   private int answer;
   private String username;
   private Date date;
   private Compute compute;
   
   
   @Inject
	private UserEJB userEJB;
   
   
   public String compute() {
	   
	   
	   FacesContext context = FacesContext.getCurrentInstance();
	   
	   int x = 2;
	   int y = 2;
	   
	  Function At = new Function("At(x,y) ="+equation);
	  Expression e = new Expression("At("+x+","+y+")" ,At);
	  
	   answer = (int) e.calculate();
	   username = (String)context.getExternalContext().getSessionMap().get("username");
	   date = new Date();
	   compute = new Compute(equation, answer, username, date);
	   userEJB.createBasic(compute);
	   log.info("CurrentDate" +date);
	   log.info("Username" +username);
	  log.info("The answer" +answer);
	   
	   return "";
   }
   
   
   


public Compute getCompute() {
	return compute;
}





public void setCompute(Compute compute) {
	this.compute = compute;
}





public Date getDate() {
	return date;
}





public void setDate(Date date) {
	this.date = date;
}





public String getUsername() {
	return username;
}





public void setUsername(String username) {
	this.username = username;
}





public String getEquation() {
	return equation;
}


public void setEquation(String equation) {
	this.equation = equation;
}


public double getAnswer() {
	return answer;
}


public void setAnswer(int answer) {
	this.answer = answer;
}


   
   
   


}
