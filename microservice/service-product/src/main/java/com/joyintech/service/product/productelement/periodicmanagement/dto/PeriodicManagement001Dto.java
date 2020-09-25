package com.joyintech.service.product.productelement.periodicmanagement.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class PeriodicManagement001Dto extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 元素Id
	 */
	private String elementId;
	
	/**
	 * 操作类型
	 */
	private String operateType;
	
	private String pageModel;
	 
	/**
	 * 有效状态
	 */
	private String validStatus;
	
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
	
	/**
	 * 期限现金流Id
	 */
	private String periodCashId;
	
	/**
	 * 费用代码
	 */
	private String feeId;
	
	/**
	 * 产品简称
	 */
	private String finprodAbbr;
	
	/**
	 * 币种
	 */
	private String ccy;
	
	/**
	 * 分支序号
	 */
	private String Branch;
	
	/**
	 * 期次开始日
	 */
	private Date startDate;
	
	/**
	 * 期次结束日
	 */
	private Date endDate;
	
	/**
	 * 利率类型
	 */
	private String rateType;
	
	/**
	 * 业绩基准
	 */
	private String baseRule;
	
	/**
	 * 投资周期
	 */
	private String investCycle;
	
	/**
	 * 期限品种
	 */
	private String termType;
	
	/**
	 * 收益类型
	 */
	private String profitType;
	
	private String publishType;
	
	private BigDecimal rateLower;
	
	private BigDecimal rateLimit;
	
	private BigDecimal rate;
	
	private String indexId;
	
	private BigDecimal coefficient;
	
	private BigDecimal spreadRate;
	
	private String resetType;
	
	private Date effDate;

	/**
     * 获取 effDate
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
 
    /**
	 * 备注
	 */
	private String remark;


	public String getElementId() {
		return elementId;
	}


	public void setElementId(String elementId) {
		this.elementId = elementId;
	}


	public String getOperateType() {
		return operateType;
	}


	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}


	public String getPageModel() {
		return pageModel;
	}


	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}


	public String getValidStatus() {
		return validStatus;
	}


	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}


	public String getFinprodId() {
		return finprodId;
	}


	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}


	public String getPeriodId() {
		return periodId;
	}


	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}


	public String getPeriodProdId() {
		return periodProdId;
	}


	public void setPeriodProdId(String periodProdId) {
		this.periodProdId = periodProdId;
	}


	public String getPeriodCashId() {
		return periodCashId;
	}


	public void setPeriodCashId(String periodCashId) {
		this.periodCashId = periodCashId;
	}


	public String getFeeId() {
		return feeId;
	}


	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}


	public String getFinprodAbbr() {
		return finprodAbbr;
	}


	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}


	public String getCcy() {
		return ccy;
	}


	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


	public String getBranch() {
		return Branch;
	}


	public void setBranch(String branch) {
		Branch = branch;
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


	public String getRateType() {
		return rateType;
	}


	public void setRateType(String rateType) {
		this.rateType = rateType;
	}


	public String getBaseRule() {
		return baseRule;
	}


	public void setBaseRule(String baseRule) {
		this.baseRule = baseRule;
	}


	public String getInvestCycle() {
		return investCycle;
	}


	public void setInvestCycle(String investCycle) {
		this.investCycle = investCycle;
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

	
	public String getPublishType() {
		return publishType;
	}


	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}


	public BigDecimal getRateLower() {
		return rateLower;
	}


	public void setRateLower(BigDecimal rateLower) {
		this.rateLower = rateLower;
	}


	public BigDecimal getRateLimit() {
		return rateLimit;
	}


	public void setRateLimit(BigDecimal rateLimit) {
		this.rateLimit = rateLimit;
	}


	public BigDecimal getRate() {
		return rate;
	}


	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public String getIndexId() {
		return indexId;
	}


	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}


	public BigDecimal getCoefficient() {
		return coefficient;
	}


	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}


	public BigDecimal getSpreadRate() {
		return spreadRate;
	}


	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}


	public String getResetType() {
		return resetType;
	}


	public void setResetType(String resetType) {
		this.resetType = resetType;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
