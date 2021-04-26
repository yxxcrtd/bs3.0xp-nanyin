/*
 * 文件名：FeeInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年7月13日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.settle.common.dto;

import java.math.BigDecimal;

/**
 * 费用信息DTO
 * @description 
 * @author 王波
 * @version 1.0 2020年7月13日
 * @see FeeInfo
 * @since 1.0
 */
public class FeeInfo {
	
	/**
	 * 费用类型
	 */
	private String feeType;
	
	/**
	 * 费用金额
	 */
	private BigDecimal feeAmount;

	/**
	 * 是否随交易支付
	 */
	private String isPayWithTrade;

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getIsPayWithTrade() {
		return isPayWithTrade;
	}

	public void setIsPayWithTrade(String isPayWithTrade) {
		this.isPayWithTrade = isPayWithTrade;
	}
}
