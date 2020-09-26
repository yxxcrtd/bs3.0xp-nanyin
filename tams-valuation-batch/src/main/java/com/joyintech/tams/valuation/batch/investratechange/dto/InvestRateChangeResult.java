/*
 * 文件名：MoneyTaAutoCalResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.investratechange.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.MstValIrs;
import com.joyintech.tams.valuation.common.dto.DfDealDto;

/**
 * 利率互换估值批处理dto
 * @author taoran
 * @version 20200114
 * @see InvestRateChangeResult
 * @since 1.0
 */
public class InvestRateChangeResult extends NeedToWriteEntity {
	
	/**
	 * 利率互换交易数据
	 */
	private List<DfDealDto> rateChangeTradeList;
	
	/**
	 * 利率互换估值数据
	 */
	private List<MstValIrs> mstValIrsList;

	public List<DfDealDto> getRateChangeTradeList() {
		return rateChangeTradeList;
	}

	public void setRateChangeTradeList(List<DfDealDto> rateChangeTradeList) {
		this.rateChangeTradeList = rateChangeTradeList;
	}

	public List<MstValIrs> getMstValIrsList() {
		return mstValIrsList;
	}

	public void setMstValIrsList(List<MstValIrs> mstValIrsList) {
		this.mstValIrsList = mstValIrsList;
	}
	
}
