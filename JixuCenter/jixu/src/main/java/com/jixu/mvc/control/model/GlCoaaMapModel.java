package com.jixu.mvc.control.model;

import com.orm.obj.GlCoaaMap;


public class GlCoaaMapModel extends GlCoaaMap{
	private static final long serialVersionUID = 1L;
	
	public GlCoaaMapModel(int id, int columnNum, String acctNum, String acctName) {
		setId(id);
		setColumnNum(columnNum);
		setAcctNum(acctNum);
		setAcctname(acctName);
	}
	
	public GlCoaaMapModel() {
	}

	private String acctname;

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}
}
