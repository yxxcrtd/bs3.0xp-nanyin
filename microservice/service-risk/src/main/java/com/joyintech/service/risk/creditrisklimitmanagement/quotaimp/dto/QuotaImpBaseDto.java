package com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.CreditLines;

public class QuotaImpBaseDto  extends CreditLines{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用于指向分项 表的Map的key值
	private Integer point;
	
	//用于排序的期限，以天为单位
	private BigDecimal sortTerm;
	
	private String creditTypeName;
	
	private String configKey;
	

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public BigDecimal getSortTerm() {
		return sortTerm;
	}

	public void setSortTerm(BigDecimal sortTerm) {
		this.sortTerm = sortTerm;
	}

	public String getCreditTypeName() {
		return creditTypeName;
	}

	public void setCreditTypeName(String creditTypeName) {
		this.creditTypeName = creditTypeName;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	
}
