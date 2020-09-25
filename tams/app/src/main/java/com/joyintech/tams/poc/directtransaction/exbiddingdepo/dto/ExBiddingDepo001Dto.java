package com.joyintech.tams.poc.directtransaction.exbiddingdepo.dto;


import com.joyintech.tams.common.base.entity.EtrEntrust;

public class ExBiddingDepo001Dto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8688635285138486753L;

	private String termType;

	private String abbr;

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}
	
	
}
