package com.emmanuel.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.emmanuel.basic.Calculator;
import com.emmanuel.basic.Calculator.OPERATION;
import com.emmanuel.ejb.UserEJB;
import com.emmanuel.entity.Compute;


@ManagedBean
@SessionScoped
public class SimpleView implements Serializable {

	
	private static final long serialVersionUID = -9113429571243325366L;
	
	private static Logger log = Logger.getLogger(SimpleView.class.getName());
	
	@Inject
	private UserEJB userEJB;
	
	private int first;
	private int second;
	private String operator;
	private Calculator calculator;
	private Compute basicCalc;
	private int answer;
	
	
	public String calculate() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		String username = (String)context.getExternalContext().getSessionMap().get("username");
		Date date = new Date();
		String request = first + operator+ second;
		
		log.info(request);
		log.info("Username" + username);
		
		if(operator.equals("+")) {
			calculator = new Calculator(OPERATION.ADD);
			answer = calculator.calculate(first, second);
			log.info("The is answer" + answer);
			basicCalc = new Compute(request, answer, username, date);
			userEJB.createBasic(basicCalc);
		}else if(operator.equals("-")) {
			calculator = new Calculator(OPERATION.SUBTRACT);
			answer = calculator.calculate(first, second);
			basicCalc = new Compute(request, answer, username, date);
			userEJB.createBasic(basicCalc);
			log.info("The is answer" + answer);
		}else if(operator.equals("/")) {
			calculator = new Calculator(OPERATION.DIVIDE);
			answer = calculator.calculate(first, second);
			basicCalc = new Compute(request, answer, username, date);
			userEJB.createBasic(basicCalc);
			log.info("The is answer" + answer);
		}else if(operator.equals("*")) {
			calculator = new Calculator(OPERATION.MULTIPLY);
			answer = calculator.calculate(first, second);
			basicCalc = new Compute(request, answer, username, date);
			userEJB.createBasic(basicCalc);
			log.info("The is answer" + answer);
		}else {
			return "";
		}
		
		return "";
	
	}
	
	
	
	
	


	public int getAnswer() {
		return answer;
	}




	public void setAnswer(int answer) {
		this.answer = answer;
	}




	public int getFirst() {
		return first;
	}


	public void setFirst(int first) {
		this.first = first;
	}


	public int getSecond() {
		return second;
	}


	public void setSecond(int second) {
		this.second = second;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public Calculator getCalculator() {
		return calculator;
	}


	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}


	public Compute getBasicCalc() {
		return basicCalc;
	}


	public void setBasicCalc(Compute basicCalc) {
		this.basicCalc = basicCalc;
	}
	
	

	
	



	

}
