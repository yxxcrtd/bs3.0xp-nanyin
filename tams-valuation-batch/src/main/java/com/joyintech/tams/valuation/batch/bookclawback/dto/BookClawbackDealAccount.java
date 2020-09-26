package com.joyintech.tams.valuation.batch.bookclawback.dto;

import java.math.BigDecimal;

public class BookClawbackDealAccount {

	/**
	 * 回补金额
	 */
	private BigDecimal clawbackAmt;

	/**
	 * 回补费用类型
	 */
	private String feeTyep;

	/**
	 * 账务明细代码
	 */
	private String bokDetailId;

	/**
	 * 本位币
	 */
	private String standardCcy;

	public BigDecimal getClawbackAmt() {
		return clawbackAmt;
	}

	public void setClawbackAmt(BigDecimal clawbackAmt) {
		this.clawbackAmt = clawbackAmt;
	}

	public String getFeeTyep() {
		return feeTyep;
	}

	public void setFeeTyep(String feeTyep) {
		this.feeTyep = feeTyep;
	}

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	public String getStandardCcy() {
		return standardCcy;
	}

	public void setStandardCcy(String standardCcy) {
		this.standardCcy = standardCcy;
	}

}
