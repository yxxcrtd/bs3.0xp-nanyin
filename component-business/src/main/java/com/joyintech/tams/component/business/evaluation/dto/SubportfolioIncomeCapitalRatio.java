package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;

/**
 * 子组合收益资金占比计算Dto
 * 子组合资金明细
 */
public class SubportfolioIncomeCapitalRatio {

	private String subcombinedCode; // 子组合代码
	private BigDecimal transactionAmount; // 交易金额
	
	public String getSubcombinedCode() {
		return subcombinedCode;
	}
	public void setSubcombinedCode(String subcombinedCode) {
		this.subcombinedCode = subcombinedCode;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
}
