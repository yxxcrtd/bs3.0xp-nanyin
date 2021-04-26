/*
 * 文件名：TrdDetailDto.java
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

import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 交易明细DTO
 * 
 * @description
 * @author 王波
 * @version 1.0 2020年7月11日
 * @see TrdDetailDto
 * @since 1.0
 */
public class TrdDetailDto extends TrdProductDeal {

		// List<交易费用明细表扩展>
	private List<TrdTradeFeeDetailExtDto> listTrdTradeFeeDetailExtDto;

	// 期限品种
	private String termType;

	/**
	 * 是否多券种
	 */
	private String isMlti;
	
	/**
	 * 成交编号
	 */
	private String dealBussId;
	
	/**
     * 申请编号
     */
    private String applyTradeId;
    
    /**
	 * 佣金计算过程中费用金额
	 */
	private BigDecimal feeAmount;

	public List<TrdTradeFeeDetailExtDto> getListTrdTradeFeeDetailExtDto() {
		return listTrdTradeFeeDetailExtDto;
	}

	public void setListTrdTradeFeeDetailExtDto(List<TrdTradeFeeDetailExtDto> listTrdTradeFeeDetailExtDto) {
		this.listTrdTradeFeeDetailExtDto = listTrdTradeFeeDetailExtDto;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getIsMlti() {
		return isMlti;
	}

	public void setIsMlti(String isMlti) {
		this.isMlti = isMlti;
	}

	public String getDealBussId() {
		return dealBussId;
	}

	public void setDealBussId(String dealBussId) {
		this.dealBussId = dealBussId;
	}

	public String getApplyTradeId() {
		return applyTradeId;
	}

	public void setApplyTradeId(String applyTradeId) {
		this.applyTradeId = applyTradeId;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
}
