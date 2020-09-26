package com.joyintech.tams.valuation.batch.bookclawback.dto;

import java.math.BigDecimal;

public class BookClawbackDto {

	/**
	 * 利息金额
	 */
	private BigDecimal intAmt;

	/**
	 * 回补费用类型
	 */
	private String feeType;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 账务明细代码
	 */
	private String bokDetailId;

	/**
	 * 交易状态
	 */
	private String tradeStatus;

	/**
	 * 金融产品代码
	 */
	private String finprodId;

	public BigDecimal getIntAmt() {
		return intAmt;
	}

	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	/**
	 * 获取 finprodId
	 * @return finprodId.
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置 finprodId
	 * @param finprodId finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

}