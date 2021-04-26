/*
 * 文件名：CalcParamDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.settle.common.dto;


import java.math.BigDecimal;

/**
 * 计算参数类型
 */
public class CalcParamDto {
	/**
	 * 计费类型
	 */
	private String chargeKind;

	/**
	 * 计算金额
	 */
	private BigDecimal jsje;


	/**
	 * 计算利率
	 */
	private BigDecimal calcRate;

	/**
	 * 保留位数
	 */
	private int retentionBit;

	/**
	 * 上限
	 */
	private BigDecimal feeMaxi;

	/**
	 * 费用下限
	 */
	private BigDecimal feeMini;

	/**
	 * 固定费用
	 */
	private BigDecimal expenseAmount;

	public String getChargeKind() {
		return chargeKind;
	}

	public void setChargeKind(String chargeKind) {
		this.chargeKind = chargeKind;
	}

	public BigDecimal getJsje() {
		return jsje;
	}

	public void setJsje(BigDecimal jsje) {
		this.jsje = jsje;
	}

	public BigDecimal getCalcRate() {
		return calcRate;
	}

	public void setCalcRate(BigDecimal calcRate) {
		this.calcRate = calcRate;
	}

	public int getRetentionBit() {
		return retentionBit;
	}

	public void setRetentionBit(int retentionBit) {
		this.retentionBit = retentionBit;
	}

	public BigDecimal getFeeMaxi() {
		return feeMaxi;
	}

	public void setFeeMaxi(BigDecimal feeMaxi) {
		this.feeMaxi = feeMaxi;
	}

	public BigDecimal getFeeMini() {
		return feeMini;
	}

	public void setFeeMini(BigDecimal feeMini) {
		this.feeMini = feeMini;
	}

	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(BigDecimal expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
}
