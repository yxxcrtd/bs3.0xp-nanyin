package com.joyintech.tams.valuation.report.common.dto;

import java.math.BigDecimal;

public class NetvaluerResultDto {
  
	/**
	 * 年月日期
	 */
	private String  yDate;
	
	/**
	 * 累积万份收益
	 */
	private BigDecimal swYield;

	public String getyDate() {
		return yDate;
	}

	public void setyDate(String yDate) {
		this.yDate = yDate;
	}

	public BigDecimal getSwYield() {
		return swYield;
	}

	public void setSwYield(BigDecimal swYield) {
		this.swYield = swYield;
	}
	
	
	
	
}