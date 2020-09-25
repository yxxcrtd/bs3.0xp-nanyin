package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;

/**
 * 子组合资金占比明细
 */
public class SubportfolioFundProportionDetails {

	private String subcombinedCode; // 子组合代码
	private BigDecimal subportfolioCapitalProportion; // 子组合资金占比
	private BigDecimal subportfolioIntervalAnnualized; // 子组合区间年化收益率
	private BigDecimal subportfolioIncomeContribution; // 子组合收益贡献
	
	public String getSubcombinedCode() {
		return subcombinedCode;
	}
	public void setSubcombinedCode(String subcombinedCode) {
		this.subcombinedCode = subcombinedCode;
	}
	public BigDecimal getSubportfolioCapitalProportion() {
		return subportfolioCapitalProportion;
	}
	public void setSubportfolioCapitalProportion(BigDecimal subportfolioCapitalProportion) {
		this.subportfolioCapitalProportion = subportfolioCapitalProportion;
	}
	public BigDecimal getSubportfolioIntervalAnnualized() {
		return subportfolioIntervalAnnualized;
	}
	public void setSubportfolioIntervalAnnualized(BigDecimal subportfolioIntervalAnnualized) {
		this.subportfolioIntervalAnnualized = subportfolioIntervalAnnualized;
	}
	public BigDecimal getSubportfolioIncomeContribution() {
		return subportfolioIncomeContribution;
	}
	public void setSubportfolioIncomeContribution(BigDecimal subportfolioIncomeContribution) {
		this.subportfolioIncomeContribution = subportfolioIncomeContribution;
	}
}
