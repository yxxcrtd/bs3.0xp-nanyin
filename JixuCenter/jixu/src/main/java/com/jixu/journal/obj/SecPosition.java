package com.jixu.journal.obj;

import java.io.Serializable;

public class SecPosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2583590609982607045L;
	
	private String cusip;
	private int currId;
	private int glgroup;


	public SecPosition(String cusip, int currId, int glgroup) {
		setCusip(cusip);
		setCurrId(currId);
		setGlgroup(glgroup);
	}
	
	
	public int getCurrId() {
		return currId;
	}

	public void setCurrId(int currId) {
		this.currId = currId;
	}

	public int getGlgroup() {
		return glgroup;
	}

	public void setGlgroup(int glgroup) {
		this.glgroup = glgroup;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

}
