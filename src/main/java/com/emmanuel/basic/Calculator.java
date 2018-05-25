package com.emmanuel.basic;

public class Calculator implements Arithmatic{

	private static Arithmatic arithmatic;
	
	public Calculator() {}

	public enum OPERATION{
		ADD {
			@Override
			public void setArithmaticOperation() {
				arithmatic = new Addition();
			}
		},
		SUBTRACT {
			@Override
			public void setArithmaticOperation() {
				arithmatic = new Subtraction();
			}
		},
		MULTIPLY {
			@Override
			public void setArithmaticOperation() {
				arithmatic = new Multiplication();
			}
		},
		DIVIDE {
			@Override
			public void setArithmaticOperation() {
				arithmatic = new Division();
			}
		};
		public abstract void setArithmaticOperation();
	}
	
	


	private OPERATION defaultOperation;

	public Calculator(OPERATION operation) {
		this.defaultOperation = operation;
		this.defaultOperation.setArithmaticOperation();
	}

	public void changeState(OPERATION operation){
		this.defaultOperation = operation;
		this.defaultOperation.setArithmaticOperation();
	}


	public int calculate(int a, int b) {
		return Calculator.arithmatic.calculate(a, b);
	}

}
