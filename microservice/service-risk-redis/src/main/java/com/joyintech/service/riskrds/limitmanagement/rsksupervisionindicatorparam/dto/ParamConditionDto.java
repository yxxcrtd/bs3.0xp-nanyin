package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto;

public class ParamConditionDto {
	/**
	 * 条件
	 */
	private String code;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 连接符
	 */
	private String linkType;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
	
}
