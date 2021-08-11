package com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.CreditConfInfo;

public class QuotaCreditConfInfoDto extends CreditConfInfo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String creditTypeName;
	
	private Integer excelPointer;
	
	//用于排序的期限，以天为单位
	private BigDecimal sortTerm;
	
	private String configKey;
	

	public String getCreditTypeName() {
		return creditTypeName;
	}

	public void setCreditTypeName(String creditTypeName) {
		this.creditTypeName = creditTypeName;
	}

	public Integer getExcelPointer() {
		return excelPointer;
	}

	public void setExcelPointer(Integer excelPointer) {
		this.excelPointer = excelPointer;
	}

	public BigDecimal getSortTerm() {
		return sortTerm;
	}

	public void setSortTerm(BigDecimal sortTerm) {
		this.sortTerm = sortTerm;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	
}
