package com.joyintech.service.riskrds.limitmanagement.rskapproval.dto;

import com.joyintech.tams.common.base.entity.RskIsupTrial;

public class RskIsupTrialDto extends RskIsupTrial {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2810818144845870551L;

	private String indName;
	
	private String indType;

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
	}

	public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}
}
