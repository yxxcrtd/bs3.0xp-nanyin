package com.joyintech.service.riskrds.limitmanagement.bankasset.dto;

import com.joyintech.tams.common.base.entity.MstBankNetvalue;

public class BankAssetDto extends MstBankNetvalue{
	
	/**
	 * 市场名称
	 */
	private String customerName;
	
	private String statusLast;
	//组织结构/社会统一信用代码
	private String insitituteCode;  

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatusLast() {
		return statusLast;
	}

	public void setStatusLast(String statusLast) {
		this.statusLast = statusLast;
	}

	public String getInsitituteCode() {
		return insitituteCode;
	}

	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}
	
	
	
	
}
