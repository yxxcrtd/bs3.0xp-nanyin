package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;

/**
 * 子组合区间收益贡献率明细
 */
public class SubportfolioRangeIncomeContributionRateDetails {

	private BigDecimal subportfolioTargetReturnContribution; // 子组合目标收益贡献
	private String combinedCode; // 组合代码

	/**
	 * 子组合代码
	 */
	private String subcombinedCode;
	
	public BigDecimal getSubportfolioTargetReturnContribution() {
		return subportfolioTargetReturnContribution;
	}
	public void setSubportfolioTargetReturnContribution(BigDecimal subportfolioTargetReturnContribution) {
		this.subportfolioTargetReturnContribution = subportfolioTargetReturnContribution;
	}
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
}
