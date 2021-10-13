package com.joyintech.tams.business.common.pojo;

public class Message<T> {

	/**
	 * 错误码
	 */
	private String code;
	
	private String message;
	
	/**
	 * 返回数据
	 */
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
