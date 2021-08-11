package com.joyintech.sso.common;


import java.io.Serializable;

public class Message<T> implements Serializable{

	private static final long serialVersionUID = 8909160204700409989L;

	private String code = MessageCode.FAIL.getCode();

	private String message = MessageCode.FAIL.getName();
	
	private T data;
	
	/**
	 * 默认构造方法
	 */
	public Message() {
	}

	public Message(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public Message(T data) {
		this.code = MessageCode.SUCCESS.getCode();
		this.message = MessageCode.SUCCESS.getName();
		this.data = data;
	}
	
	public Message(MessageCode messageCode) {
		this.code = messageCode.getCode();
		this.message = messageCode.getName();
	}

	public Message<T> success(){
		this.code = MessageCode.SUCCESS.getCode();
		this.message = MessageCode.SUCCESS.getName();
		return this;
	}
	
	public Message<T> success(String message){
		this.code = MessageCode.SUCCESS.getCode();
		this.message = message;
		return this;
	}
	
	public Message<T> fail(){
		this.code = MessageCode.FAIL.getCode();
		this.message = MessageCode.FAIL.getName();
		return this;
	} 
	
	public Message<T> fail(String code, String message){
		this.code = code;
		this.message = message;
		return this;
	} 
	
	public Message<T> fail(MessageCode messageCode){
		this.code = messageCode.getCode();
		this.message = messageCode.getName();
		return this;
	} 
	
	public String getCode() {
		return code;
	}

	public Message<T> setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Message<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public Message<T> setData(T data) {
		this.data = data;
		return this;
	}
	
}
