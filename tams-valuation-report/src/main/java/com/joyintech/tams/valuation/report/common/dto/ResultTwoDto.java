package com.joyintech.tams.valuation.report.common.dto;

import java.math.BigDecimal;

public class ResultTwoDto {
	/**
	 * 累积净值收益率
	 */
       private BigDecimal profit;
       
       /**
        *净值收益率标准差 
        */
     private BigDecimal profitStandard;
     
     /**
      * 业绩比较基准收益率
      */
     private BigDecimal floatRate;
     
     /**
      * 业绩比较基准收益率标准差
      */
     private  BigDecimal floatRateStandard;
     
     /**
      * 份额净值增长率
      */
    private  BigDecimal  proShareRate;
    
    /**
     * 份额净值收益率
     */
    private  BigDecimal  currentValue;
    /**
     * 净值增长率标准差
     */
    private  BigDecimal     productGrowth;
    
    /**
     *阶段净值增长率 
     */
    private  BigDecimal  stageRate;
    
	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getProfitStandard() {
		return profitStandard;
	}

	public void setProfitStandard(BigDecimal profitStandard) {
		this.profitStandard = profitStandard;
	}

	public BigDecimal getFloatRate() {
		return floatRate;
	}

	public void setFloatRate(BigDecimal floatRate) {
		this.floatRate = floatRate;
	}

	public BigDecimal getFloatRateStandard() {
		return floatRateStandard;
	}

	public void setFloatRateStandard(BigDecimal floatRateStandard) {
		this.floatRateStandard = floatRateStandard;
	}

	public BigDecimal getProShareRate() {
		return proShareRate;
	}

	public void setProShareRate(BigDecimal proShareRate) {
		this.proShareRate = proShareRate;
	}

	public BigDecimal getProductGrowth() {
		return productGrowth;
	}

	public void setProductGrowth(BigDecimal productGrowth) {
		this.productGrowth = productGrowth;
	}

	public BigDecimal getStageRate() {
		return stageRate;
	}

	public void setStageRate(BigDecimal stageRate) {
		this.stageRate = stageRate;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}
       
       
}
