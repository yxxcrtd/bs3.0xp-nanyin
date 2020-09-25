package com.joyintech.service.product.productelement.periodicmanagement.dto;

import java.math.BigDecimal;

public class FinBaseRuleDto {
	
	private String rateType;
	
	private BigDecimal rate;
	
	private String indexId;
	
	private String indexSource;
	
	private String resetType;
	
	private BigDecimal coefficient;
	
	private BigDecimal spreadRate;
	
	private BigDecimal rateLower;
	
	private BigDecimal rateLimit;
	
	private String publishType;
	
	private String remark;

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
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

	public String getIndexSource() {
		return indexSource;
	}

	public void setIndexSource(String indexSource) {
		this.indexSource = indexSource;
	}

	public String getResetType() {
		return resetType;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
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

	public String getPublishType() {
		return publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
