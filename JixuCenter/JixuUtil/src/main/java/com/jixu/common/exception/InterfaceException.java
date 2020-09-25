package com.jixu.common.exception;

public class InterfaceException extends Exception {

	private static final long serialVersionUID = 4576453902374088273L;

	private int exceptionType = 0;
	
	public int getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	public InterfaceException() {
		// TODO Auto-generated constructor stub
	}

	public InterfaceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public InterfaceException(int errorType, String arg0) {
		super(arg0);
		this.setExceptionType(errorType);
		
	}

	public InterfaceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InterfaceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
