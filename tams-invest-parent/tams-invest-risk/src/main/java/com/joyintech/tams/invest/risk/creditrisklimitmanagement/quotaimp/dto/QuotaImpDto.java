package com.joyintech.tams.invest.risk.creditrisklimitmanagement.quotaimp.dto;

import com.joyintech.tams.common.base.entity.CreditLines;

public class QuotaImpDto extends CreditLines{
	
	private String termStr;
	
	private String amountName;
	
	private String customerName;
	
	private String amountType;
	


	public String getTermStr() {
		return termStr;
	}

	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}

	public String getAmountName() {
		return amountName;
	}

	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	

}
