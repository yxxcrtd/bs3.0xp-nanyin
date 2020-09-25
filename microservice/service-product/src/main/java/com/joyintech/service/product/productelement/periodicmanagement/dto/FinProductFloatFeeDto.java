package com.joyintech.service.product.productelement.periodicmanagement.dto;

import java.util.Date;

public class FinProductFloatFeeDto {
	
	private String feeId;
	
	private String baseRule;
	
	private String basicBill;
	
	private String basis;
	
	private String astVdateType;
	
	private Date fAccrDAte;
	
	private String accrType;
	
	private String intervalRule;
	
	private Date fAccrEndDate;
	
	private String accrDayType;
	
	private Date eAccrDate;
	
	private Date firstPayDate;
	
	private String endTdayRule;
	
	private String payTdayRule;
	
	private String payCycle;
	
	private String periodCashId;
	
	private String remark;
	
	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getBaseRule() {
		return baseRule;
	}

	public void setBaseRule(String baseRule) {
		this.baseRule = baseRule;
	}

	public String getBasicBill() {
		return basicBill;
	}

	public void setBasicBill(String basicBill) {
		this.basicBill = basicBill;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getPeriodCashId() {
		return periodCashId;
	}

	public void setPeriodCashId(String periodCashId) {
		this.periodCashId = periodCashId;
	}

	public String getAstVdateType() {
		return astVdateType;
	}

	public void setAstVdateType(String astVdateType) {
		this.astVdateType = astVdateType;
	}

	public Date getfAccrDAte() {
		return fAccrDAte;
	}

	public void setfAccrDAte(Date fAccrDAte) {
		this.fAccrDAte = fAccrDAte;
	}

	public String getAccrType() {
		return accrType;
	}

	public void setAccrType(String accrType) {
		this.accrType = accrType;
	}

	public String getIntervalRule() {
		return intervalRule;
	}

	public void setIntervalRule(String intervalRule) {
		this.intervalRule = intervalRule;
	}

	public Date getfAccrEndDate() {
		return fAccrEndDate;
	}

	public void setfAccrEndDate(Date fAccrEndDate) {
		this.fAccrEndDate = fAccrEndDate;
	}

	public String getAccrDayType() {
		return accrDayType;
	}

	public void setAccrDayType(String accrDayType) {
		this.accrDayType = accrDayType;
	}

	public Date geteAccrDate() {
		return eAccrDate;
	}

	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
	}

	public Date getFirstPayDate() {
		return firstPayDate;
	}

	public void setFirstPayDate(Date firstPayDate) {
		this.firstPayDate = firstPayDate;
	}

	public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}

	public String getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
