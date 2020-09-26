/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productinfoquery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 测试指标页面Dto
 * 
 * @author 
 * @version 
 * @see ValProductInfoQuery001TestDto
 * @since 1.0
 */
public class ValProductInfoQuery001TestDto extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
	
	/**
	 * 加权平均产品份额本期利润
	 */
	private BigDecimal mavalue;
	
	 /**
	  * 加权平均单位本期净收益
	  */
	private BigDecimal wavlue;
	
	/**
	 * 累计净值增长率
	 */
	private BigDecimal  productShareValue;
	
	/**
	 * 累积净值收益率
	 */
       private BigDecimal profit;
       
       /**
        *份额净值收益率标准差
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
     * 份额净值增长率标准差
     */
    private  BigDecimal     productGrowth;
    
    /**
     *阶段净值增长率 
     */
    private  BigDecimal  stageRate;


	public BigDecimal getMavalue() {
		return mavalue;
	}

	public void setMavalue(BigDecimal mavalue) {
		this.mavalue = mavalue;
	}

	public BigDecimal getWavlue() {
		return wavlue;
	}

	public void setWavlue(BigDecimal wavlue) {
		this.wavlue = wavlue;
	}

	public BigDecimal getProductShareValue() {
		return productShareValue;
	}

	public void setProductShareValue(BigDecimal productShareValue) {
		this.productShareValue = productShareValue;
	}

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

	public BigDecimal getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
