package com.joyintech.tams.valuation.common.interf.howmany.dto;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;

public class BokBookdetailCurrentDto extends BokBookdetailCurrent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 业务明细类型
	 */
	private String busiDetailType;

	public String getBusiDetailType() {
		return busiDetailType;
	}

	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}
	
	
}
