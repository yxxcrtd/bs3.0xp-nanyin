package com.jixu.adapter.types;

public class CallBackResult {

	/**
	 *  	是否成功
	 */
	private boolean isCorrect;
	
	/**
	 * 	错误信息
	 */
	private String errorMsg;

	/**
	 * 	是否成功
	 * @return	是否成功
	 */
	public boolean isCorrect() {
		return isCorrect;
	}

	/**
	 * 	是否成功
	 * @param isCorrect	是否成功
	 */
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * 	错误信息
	 * @return	错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 	错误信息
	 * @param errorMsg	错误信息
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
