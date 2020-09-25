package com.jixu.mvc.control.model;

public class PortParams {
	
	private String portnum;
	
	private String navDate;
	
	private String portcode;
	
	private String extacctcode;
	
	private int bkAcctID;
	
	private String acctName;

	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public String getNavDate() {
		return navDate;
	}

	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}

	public String getPortcode() {
		return portcode;
	}

	public void setPortcode(String portcode) {
		this.portcode = portcode;
	}

	public String getExtacctcode() {
		return extacctcode;
	}

	public void setExtacctcode(String extacctcode) {
		this.extacctcode = extacctcode;
	}

	public int getBkAcctID() {
		return bkAcctID;
	}

	public void setBkAcctID(int bkAcctID) {
		this.bkAcctID = bkAcctID;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
}
