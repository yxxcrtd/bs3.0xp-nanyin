package com.joyintech.tams.valuation.report.common.dto;

import java.math.BigDecimal;

public class ResultOneDto {
  
	
	/**
	 * 累计净值收益率
	 */
	private  BigDecimal profit;
	
	
	/**
	 * 加权平均产品份额本期利润
	 */
	private BigDecimal mavalue;
	
	 /**
	  * 加权平均单位本期净收益
	  */
	private BigDecimal wavlue;
	
	/**
	 * 产品份额累计净值增长率
	 */
	private BigDecimal  productShareValue;
    
	

	public BigDecimal getProfit() {
		return profit;
	}


	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}


	public BigDecimal getMavalue() {
		return mavalue;
	}


	public void setMavalue(BigDecimal mavalue) {
		this.mavalue = mavalue;
	}


	public BigDecimal getProductShareValue() {
		return productShareValue;
	}


	public BigDecimal getWavlue() {
		return wavlue;
	}


	public void setWavlue(BigDecimal wavlue) {
		this.wavlue = wavlue;
	}


	public void setProductShareValue(BigDecimal productShareValue) {
		this.productShareValue = productShareValue;
	}
	
	
}
