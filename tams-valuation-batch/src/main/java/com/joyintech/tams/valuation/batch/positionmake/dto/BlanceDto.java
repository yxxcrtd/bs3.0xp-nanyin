package com.joyintech.tams.valuation.batch.positionmake.dto;

import java.math.BigDecimal;

/**
 * 其他应收款余额实体类
 * 
 * @author Administrator
 *
 */
public class BlanceDto {

	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 账务明细代码
	 */
	private String bookDetailId;

	/**
	 * 业务明细代码
	 */
	private String busiId;

	/**
	 * 待清算资金
	 */
	private BigDecimal dqskBlance;

	/**
	 * 待清算资金 - 本位币
	 */
	private BigDecimal dqskBlanceForBccy;

	/**
	 * 币种
	 */
	private String ccy;

	public String getBookDetailId() {
		return bookDetailId;
	}

	public void setBookDetailId(String bookDetailId) {
		this.bookDetailId = bookDetailId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public BigDecimal getDqskBlance() {
		return dqskBlance;
	}

	public void setDqskBlance(BigDecimal dqskBlance) {
		this.dqskBlance = dqskBlance;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getDqskBlanceForBccy() {
		return dqskBlanceForBccy;
	}

	public void setDqskBlanceForBccy(BigDecimal dqskBlanceForBccy) {
		this.dqskBlanceForBccy = dqskBlanceForBccy;
	}
}
