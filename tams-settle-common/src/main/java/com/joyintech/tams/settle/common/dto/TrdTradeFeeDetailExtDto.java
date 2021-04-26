/*
 * 文件名：TrdTradeFeeDetailExtDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年7月11日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.settle.common.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

/**
 * 交易费用明细表扩展
 * @description 
 * @author 王波
 * @version 1.0 2020年7月11日
 * @see TrdTradeFeeDetailExtDto
 * @since 1.0
 */
public class TrdTradeFeeDetailExtDto  extends TrdTradeFeeDetail {
	
	/**
	 * 佣金计算过程中费用金额
	 */
	private BigDecimal feeAmount;

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
}
