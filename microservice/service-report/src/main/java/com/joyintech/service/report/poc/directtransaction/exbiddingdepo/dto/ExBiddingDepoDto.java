package com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dto;


import com.joyintech.tams.common.base.entity.EtrEntrust;

public class ExBiddingDepoDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8014063105383443531L;

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
