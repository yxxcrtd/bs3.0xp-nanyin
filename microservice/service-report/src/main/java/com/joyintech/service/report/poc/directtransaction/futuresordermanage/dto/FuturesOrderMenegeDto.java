package com.joyintech.service.report.poc.directtransaction.futuresordermanage.dto;

import com.joyintech.tams.common.base.entity.EtrEntrust;

public class FuturesOrderMenegeDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6282065294073938747L;
	private String abbr;

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
}
