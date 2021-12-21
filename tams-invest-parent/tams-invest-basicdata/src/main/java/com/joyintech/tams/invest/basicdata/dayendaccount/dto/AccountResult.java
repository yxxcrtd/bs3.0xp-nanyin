package com.joyintech.tams.invest.basicdata.dayendaccount.dto;

import com.joyintech.framework.base.BaseEntity;

public class AccountResult extends BaseEntity {

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 消息类型
	 */
	private String messageType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}
