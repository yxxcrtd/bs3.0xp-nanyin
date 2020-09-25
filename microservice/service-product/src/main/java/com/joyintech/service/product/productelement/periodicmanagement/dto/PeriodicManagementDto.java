package com.joyintech.service.product.productelement.periodicmanagement.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class PeriodicManagementDto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 有效状态
	 */
	private String effectStatus;
	
	/**
	 * 业绩基准
	 */
	private String baseRule;

	/**
	 * 产品代码
	 */
	private String finprodId;
	
	/**
	 * 期次代码
	 */
	private String periodId;
	
	/**
	 * 期限产品代码
	 */
	private String periodProdId;
	
	private String finprodAbbr;
	
	private String investCycle;
	
	private String ccy;
	
	private String termType;
	
	private String profitType;
	
	/**
	 * 利率类型
	 */
	private String rateType;
	
	private Date startDate;
	
	private Date endDate;

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) { 	
		this.finprodAbbr = finprodAbbr;
	}

	public String getInvestCycle() {
		return investCycle;
	}

	public void setInvestCycle(String investCycle) {
		this.investCycle = investCycle;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getPeriodProdId() {
		return periodProdId;
	}

	public void setPeriodProdId(String periodProdId) {
		this.periodProdId = periodProdId;
	}

	public String getEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}

	public String getBaseRule() {
		return baseRule;
	}

	public void setBaseRule(String baseRule) {
		this.baseRule = baseRule;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
