/*
 * 文件名：LendTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年8月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 拆借交易 DTO
 * 
 * @author zhangwei
 * @version 1.0 2018年8月1日
 * @see LendTradeDto
 * @since 1.0
 */
public class LendTradeDto extends InvestTradeDto {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 计息基础
	 */
	private String basis;

	/** 本金收益特征 */
	private String capiIncomeFeature;

	/** 出/收款账户 - 对手方资金账户 **/
	private String myAcctName;

	/** 对手方资金账户 **/
	private String counterAcctName;

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getMyAcctName() {
		return myAcctName;
	}

	public void setMyAcctName(String myAcctName) {
		this.myAcctName = myAcctName;
	}

	public String getCounterAcctName() {
		return counterAcctName;
	}

	public void setCounterAcctName(String counterAcctName) {
		this.counterAcctName = counterAcctName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCapiIncomeFeature() {
		return SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, this.capiIncomeFeature);
	}

	public void setCapiIncomeFeature(String capiIncomeFeature) {
		this.capiIncomeFeature = capiIncomeFeature;
	}

}
