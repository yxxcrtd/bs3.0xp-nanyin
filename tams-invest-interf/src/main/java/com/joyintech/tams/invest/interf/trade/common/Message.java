package com.joyintech.tams.invest.interf.trade.common;

/**
 * 消息对象
 * 
 * @author yflv
 * @version 1.0 2021年09月23日
 * @see Result
 * @since 1.0
 */
public class Message {

	public static final Message SUCCESS = new Message("0","成功");
	public static final Message FAIL = new Message("fail","未知异常");
	
	protected String code;
	protected String name;
	protected Message(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
