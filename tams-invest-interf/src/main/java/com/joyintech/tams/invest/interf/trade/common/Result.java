package com.joyintech.tams.invest.interf.trade.common;

import java.io.Serializable;

/**
 * 返回结果对象
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see Result
 * @since 1.0
 */
public class Result<T> implements Serializable{

	private static final long serialVersionUID = 8909160204700409989L;

	/**
	 * 响应码
	 */
	private String code = Message.FAIL.getCode();

	/**
	 * 消息
	 */
	private String message = Message.FAIL.getName();
	
	/**
	 * 数据
	 */
	private T data;
	
	/**
	 * 默认构造方法
	 */
	public Result() {
	}

	public Result(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public Result(T data) {
		this.code = Message.SUCCESS.getCode();
		this.message = Message.SUCCESS.getName();
		this.data = data;
	}
	
	public Result(Message messageCode) {
		this.code = messageCode.getCode();
		this.message = messageCode.getName();
	}

	public Result<T> success(){
		this.code = Message.SUCCESS.getCode();
		this.message = Message.SUCCESS.getName();
		return this;
	}
	
	public Result<T> success(String message){
		this.code = Message.SUCCESS.getCode();
		this.message = message;
		return this;
	}
	
	public Result<T> fail(){
		this.code = Message.FAIL.getCode();
		this.message = Message.FAIL.getName();
		return this;
	} 
	
	public Result<T> fail(String code, String message){
		this.code = code;
		this.message = message;
		return this;
	} 
	
	public Result<T> fail(Message messageCode){
		this.code = messageCode.getCode();
		this.message = messageCode.getName();
		return this;
	} 
	
	public String getCode() {
		return code;
	}

	public Result<T> setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}
	
}
