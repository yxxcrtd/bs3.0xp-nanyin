package com.jixu.common.exception;

public class NewFundException extends Exception {

	private static final long serialVersionUID = 7078487303667156626L;
	
	private int exceptionType = 0;
	
	public int getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	public NewFundException() {
		// TODO Auto-generated constructor stub
	}

	public NewFundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public NewFundException(int errorType, String arg0) {
		super(arg0);
		this.setExceptionType(errorType);
		
	}

	public NewFundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NewFundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
