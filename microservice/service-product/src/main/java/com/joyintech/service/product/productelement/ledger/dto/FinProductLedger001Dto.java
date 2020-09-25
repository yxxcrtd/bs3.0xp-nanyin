/*
 * 文件名：FinProductLedger001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.ledger.dto;

import java.math.BigDecimal;

import com.joyintech.service.product.productelement.build.dto.FinProductBuild001Dto;

public class FinProductLedger001Dto extends FinProductBuild001Dto{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -1183580632534287917L;

	private String prodManager;
	
	private String riskLevel;
	
	private long remainDays;
	
	private String payType;
	
	private BigDecimal raiseAmtPlan;
	
	private BigDecimal subDealAmt;	
	private BigDecimal subDealNum;
	private BigDecimal trustFee;	
	private BigDecimal saleFee;
	private BigDecimal fixedFee;	
	private BigDecimal floatDiffFee;
	private BigDecimal othSaleFee;	
	private BigDecimal vatFee;
	private BigDecimal floatFee;	
	private BigDecimal othFee;
	private BigDecimal midBusFee;	
	private BigDecimal monthPubFee;
	private BigDecimal monthDueFee;

	private BigDecimal tdyUnitValue;
	
	private String durationflag;
	private String payCycle;
	
	private BigDecimal yield7;
	
	private BigDecimal tdyAssetAmt;
	
	private String floatFeeProportion;
	private BigDecimal feeFixedRate;
	private BigDecimal trustFeeRate;
	private BigDecimal customerRealRate;
	private BigDecimal customerProfit;
	
	private BigDecimal rateRepYield;
	
	private BigDecimal prodUnitValue;
	private BigDecimal prodAssetAmt;
	
	private String rateRepYieldStr;
	/**
	 * 销售手续费率
	 */
	private BigDecimal saleProcessRate;

	public BigDecimal getSaleProcessRate() {
		return saleProcessRate;
	}

	public void setSaleProcessRate(BigDecimal saleProcessRate) {
		this.saleProcessRate = saleProcessRate;
	}

	public String getRateRepYieldStr() {
		return rateRepYieldStr;
	}

	public void setRateRepYieldStr(String rateRepYieldStr) {
		this.rateRepYieldStr = rateRepYieldStr;
	}

	public BigDecimal getProdUnitValue() {
		return prodUnitValue;
	}

	public void setProdUnitValue(BigDecimal prodUnitValue) {
		this.prodUnitValue = prodUnitValue;
	}

	public BigDecimal getProdAssetAmt() {
		return prodAssetAmt;
	}

	public void setProdAssetAmt(BigDecimal prodAssetAmt) {
		this.prodAssetAmt = prodAssetAmt;
	}

	public BigDecimal getRateRepYield() {
		return rateRepYield;
	}

	public void setRateRepYield(BigDecimal rateRepYield) {
		this.rateRepYield = rateRepYield;
	}

	public String getDurationflag() {
		return durationflag;
	}

	public void setDurationflag(String durationflag) {
		this.durationflag = durationflag;
	}

	public String getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	public String getFloatFeeProportion() {
		return floatFeeProportion;
	}

	public void setFloatFeeProportion(String floatFeeProportion) {
		this.floatFeeProportion = floatFeeProportion;
	}

	public BigDecimal getFeeFixedRate() {
		return feeFixedRate;
	}

	public void setFeeFixedRate(BigDecimal feeFixedRate) {
		this.feeFixedRate = feeFixedRate;
	}

	public BigDecimal getTrustFeeRate() {
		return trustFeeRate;
	}

	public void setTrustFeeRate(BigDecimal trustFeeRate) {
		this.trustFeeRate = trustFeeRate;
	}

	public BigDecimal getCustomerRealRate() {
		return customerRealRate;
	}

	public void setCustomerRealRate(BigDecimal customerRealRate) {
		this.customerRealRate = customerRealRate;
	}

	public BigDecimal getCustomerProfit() {
		return customerProfit;
	}

	public void setCustomerProfit(BigDecimal customerProfit) {
		this.customerProfit = customerProfit;
	}

	public BigDecimal getTdyUnitValue() {
		return tdyUnitValue;
	}

	public void setTdyUnitValue(BigDecimal tdyUnitValue) {
		this.tdyUnitValue = tdyUnitValue;
	}

	public BigDecimal getYield7() {
		return yield7;
	}

	public void setYield7(BigDecimal yield7) {
		this.yield7 = yield7;
	}

	public BigDecimal getTdyAssetAmt() {
		return tdyAssetAmt;
	}

	public void setTdyAssetAmt(BigDecimal tdyAssetAmt) {
		this.tdyAssetAmt = tdyAssetAmt;
	}

	public BigDecimal getSubDealAmt() {
		return subDealAmt;
	}

	public void setSubDealAmt(BigDecimal subDealAmt) {
		this.subDealAmt = subDealAmt;
	}

	public BigDecimal getSubDealNum() {
		return subDealNum;
	}

	public void setSubDealNum(BigDecimal subDealNum) {
		this.subDealNum = subDealNum;
	}

	public BigDecimal getTrustFee() {
		return trustFee;
	}

	public void setTrustFee(BigDecimal trustFee) {
		this.trustFee = trustFee;
	}

	public BigDecimal getSaleFee() {
		return saleFee;
	}

	public void setSaleFee(BigDecimal saleFee) {
		this.saleFee = saleFee;
	}

	public BigDecimal getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(BigDecimal fixedFee) {
		this.fixedFee = fixedFee;
	}

	public BigDecimal getFloatDiffFee() {
		return floatDiffFee;
	}

	public void setFloatDiffFee(BigDecimal floatDiffFee) {
		this.floatDiffFee = floatDiffFee;
	}

	public BigDecimal getOthSaleFee() {
		return othSaleFee;
	}

	public void setOthSaleFee(BigDecimal othSaleFee) {
		this.othSaleFee = othSaleFee;
	}

	public BigDecimal getVatFee() {
		return vatFee;
	}

	public void setVatFee(BigDecimal vatFee) {
		this.vatFee = vatFee;
	}

	public BigDecimal getFloatFee() {
		return floatFee;
	}

	public void setFloatFee(BigDecimal floatFee) {
		this.floatFee = floatFee;
	}

	public BigDecimal getOthFee() {
		return othFee;
	}

	public void setOthFee(BigDecimal othFee) {
		this.othFee = othFee;
	}

	public BigDecimal getMidBusFee() {
		return midBusFee;
	}

	public void setMidBusFee(BigDecimal midBusFee) {
		this.midBusFee = midBusFee;
	}

	public BigDecimal getMonthPubFee() {
		return monthPubFee;
	}

	public void setMonthPubFee(BigDecimal monthPubFee) {
		this.monthPubFee = monthPubFee;
	}

	public BigDecimal getMonthDueFee() {
		return monthDueFee;
	}

	public void setMonthDueFee(BigDecimal monthDueFee) {
		this.monthDueFee = monthDueFee;
	}

	public String getProdManager() {
		return prodManager;
	}

	public void setProdManager(String prodManager) {
		this.prodManager = prodManager;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public long getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(long remainDays) {
		this.remainDays = remainDays;
	}

	public BigDecimal getRaiseAmtPlan() {
		return raiseAmtPlan;
	}

	public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
		this.raiseAmtPlan = raiseAmtPlan;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}	
	   //业绩比较基准（%）（浮动管理费） add by cxq 20191227
	   private String rateYieldFloat;

	   public String getRateYieldFloat() {
		   return rateYieldFloat;
	   }

	   public void setRateYieldFloat(String rateYieldFloat) {
		   this.rateYieldFloat = rateYieldFloat;
	   }
}
