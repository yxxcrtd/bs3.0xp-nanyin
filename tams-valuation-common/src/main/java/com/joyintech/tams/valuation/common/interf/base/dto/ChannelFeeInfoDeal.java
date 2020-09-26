package com.joyintech.tams.valuation.common.interf.base.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

public class ChannelFeeInfoDeal extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 通道费
	 */
	private BigDecimal chlFeeAmt;

	/**
	 * 是否随交易支付
	 */
	private String isPayWithTrade;

	/**
	 * 通道代码
	 */
	private String channelId;

	/**
	 * 产品代码
	 */
	private String prodId;

	/**
	 * 核算主体代码
	 */
	private String acctSubjectId;

	/**
	 * 核算主体类型
	 */
	private String acctSubjectType;

	public BigDecimal getChlFeeAmt() {
		return chlFeeAmt;
	}

	public void setChlFeeAmt(BigDecimal chlFeeAmt) {
		this.chlFeeAmt = chlFeeAmt;
	}

	public String getIsPayWithTrade() {
		return isPayWithTrade;
	}

	public void setIsPayWithTrade(String isPayWithTrade) {
		this.isPayWithTrade = isPayWithTrade;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getAcctSubjectId() {
		return acctSubjectId;
	}

	public void setAcctSubjectId(String acctSubjectId) {
		this.acctSubjectId = acctSubjectId;
	}

	public String getAcctSubjectType() {
		return acctSubjectType;
	}

	public void setAcctSubjectType(String acctSubjectType) {
		this.acctSubjectType = acctSubjectType;
	}

}
