package com.joyintech.tams.valuation.batch.prdindexcalculate.dto;

import java.math.BigDecimal;

public class FinBenchmarklDto {

	/**
	 * 基准类型，利率行情、复合基准等
	 */
	private   String bmType;
	
	/**
	 * 调整系数
	 */
	private BigDecimal coefficient;
	
	/**
	 * 利差
	 */
	private BigDecimal spreadRate;
	
	/**
	 * 业绩比较基准
	 */
   private BigDecimal stMance;
   
   
   /**
    * 业绩基准描述
    */
   private String stDetail;
   
   
  /**
   * 业绩基准
   */
   private  BigDecimal  rateBenchmark; 
   
   
   
   
	public BigDecimal getRateBenchmark() {
	return rateBenchmark;
}

public void setRateBenchmark(BigDecimal rateBenchmark) {
	this.rateBenchmark = rateBenchmark;
}

	public String getStDetail() {
	return stDetail;
}

public void setStDetail(String stDetail) {
	this.stDetail = stDetail;
}

	public BigDecimal getStMance() {
		return stMance;
	}

	public void setStMance(BigDecimal stMance) {
		this.stMance = stMance;
	}

	public String getBmType() {
		return bmType;
	}

	public void setBmType(String bmType) {
		this.bmType = bmType;
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
	
	
	
}


