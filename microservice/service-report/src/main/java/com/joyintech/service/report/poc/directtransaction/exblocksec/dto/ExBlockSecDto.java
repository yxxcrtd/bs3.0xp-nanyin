package com.joyintech.service.report.poc.directtransaction.exblocksec.dto;

import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.math.BigDecimal;



public class ExBlockSecDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3403199521801067271L;

	private String abbr;
	
	private Long settlespreed;
	
	private BigDecimal exerYield;
	
	private BigDecimal matureYield;

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Long getSettlespreed() {
		return settlespreed;
	}

	public void setSettlespreed(Long settlespreed) {
		this.settlespreed = settlespreed;
	}

	public BigDecimal getExerYield() {
		return exerYield;
	}

	public void setExerYield(BigDecimal exerYield) {
		this.exerYield = exerYield;
	}

	public BigDecimal getMatureYield() {
		return matureYield;
	}

	public void setMatureYield(BigDecimal matureYield) {
		this.matureYield = matureYield;
	}
	
	
}
