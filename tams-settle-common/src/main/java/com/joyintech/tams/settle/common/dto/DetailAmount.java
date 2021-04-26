/*
 * 文件名：DetailAmount.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年7月11日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.settle.common.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 明细金额
 * 
 * @description 
 * @author 王波
 * @version 1.0 2020年7月11日
 * @see DetailAmount
 * @since 1.0
 */
public class DetailAmount {
	// 占比金额
	private BigDecimal mixAmount;
	
	// 编号
	private String  detailNo;
	
	// 分配金额数组
	private BigDecimal [] allotAmountList;

	public BigDecimal getMixAmount() {
		return mixAmount;
	}

	public void setMixAmount(BigDecimal mixAmount) {
		this.mixAmount = mixAmount;
	}

	public String getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
	}

	public BigDecimal[] getAllotAmountList() {
		return allotAmountList;
	}

	public void setAllotAmountList(BigDecimal[] allotAmountList) {
		this.allotAmountList = allotAmountList;
	}
}
