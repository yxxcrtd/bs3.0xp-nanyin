package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;

/**
 * 子组合
 */
public class ChildCombination {

	private String combinedCode;
	private String subcombinedCode; // 子组合代码
	private String profitType;
	private BigDecimal disPercent; // 继承比例
	
	public String getCombinedCode() {
		return combinedCode;
	}
	public void setCombinedCode(String combinedCode) {
		this.combinedCode = combinedCode;
	}
	public String getSubcombinedCode() {
		return subcombinedCode;
	}
	public void setSubcombinedCode(String subcombinedCode) {
		this.subcombinedCode = subcombinedCode;
	}
	public String getProfitType() {
		return profitType;
	}
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	public BigDecimal getDisPercent() {
		return disPercent;
	}
	public void setDisPercent(BigDecimal disPercent) {
		this.disPercent = disPercent;
	}
}
