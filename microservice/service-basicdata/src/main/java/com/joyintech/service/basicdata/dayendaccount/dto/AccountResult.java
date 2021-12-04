package com.joyintech.service.basicdata.dayendaccount.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.StlSecRecon;

import java.math.BigDecimal;

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
