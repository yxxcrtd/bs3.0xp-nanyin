package com.joyintech.tams.invest.basicdata.mstseatinfo.dto;

public class MstCustomerDto {

	//编号
	private String id;

	//名称
	private String text;
	
	//是否券商
	private String isBroker;
	
	//是否期货公司
	private String isFutureCom;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIsBroker() {
		return isBroker;
	}

	public void setIsBroker(String isBroker) {
		this.isBroker = isBroker;
	}

	public String getIsFutureCom() {
		return isFutureCom;
	}

	public void setIsFutureCom(String isFutureCom) {
		this.isFutureCom = isFutureCom;
	}
	
	

}
