package com.joyintech.service.report.poc.directtransaction.exblockbond.dto;


import com.joyintech.tams.common.base.entity.EtrEntrust;

public class ExBlockBondDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1133405342253712035L;

	private String abbr;

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
}
