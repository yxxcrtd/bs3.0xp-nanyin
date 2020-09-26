package com.joyintech.tams.valuation.report.common.dto;

import java.math.BigDecimal;

public class PowerDto {

	/**
	 * 权值
	 */
	private  BigDecimal  powerValue;
	
	
	/**
	 * 权重
	 */
	private  BigDecimal powerWeight;


	public BigDecimal getPowerValue() {
		return powerValue;
	}


	public void setPowerValue(BigDecimal powerValue) {
		this.powerValue = powerValue;
	}


	public BigDecimal getPowerWeight() {
		return powerWeight;
	}


	public void setPowerWeight(BigDecimal powerWeight) {
		this.powerWeight = powerWeight;
	}
	
	
	
	
}
