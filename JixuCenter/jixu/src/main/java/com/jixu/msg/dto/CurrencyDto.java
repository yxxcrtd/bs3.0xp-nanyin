package com.jixu.msg.dto;

public class CurrencyDto {
	private String curID;
	
	private String curCode;
	
	private String curName;
	
	private int active;

	public String getCurID() {
		return curID;
	}

	public void setCurID(String curID) {
		this.curID = curID;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public String getCurName() {
		return curName;
	}

	public void setCurName(String curName) {
		this.curName = curName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
