package com.jixu.common.exception;

public class JNLException extends Exception {

	private static final long serialVersionUID = 7078487303667156625L;
	
	private int exceptionType = 0;
	
	public int getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	public JNLException() {
		// TODO Auto-generated constructor stub
	}

	public JNLException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public JNLException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public JNLException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
