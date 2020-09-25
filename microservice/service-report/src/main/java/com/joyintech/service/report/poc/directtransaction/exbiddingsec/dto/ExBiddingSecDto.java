package com.joyintech.service.report.poc.directtransaction.exbiddingsec.dto;


import com.joyintech.tams.common.base.entity.EtrEntrust;

public class ExBiddingSecDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6390306603950173491L;

	private String abbr;

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	
}
