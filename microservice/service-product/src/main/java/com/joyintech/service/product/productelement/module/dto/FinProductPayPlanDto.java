/*
 * 文件名：FinProductPayPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productelement.module.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 产品付息计划
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月26日
 * @see FinProductPayPlanDto
 * @since 1.0
 */
public class FinProductPayPlanDto {

	/**
	 * 现金流支付规则
	 */
	private FinCashPayRule cashPayRule;

	/**
	 * 现金流计划
	 */
	private List<FinCashPlan> cashPlanList;

	public FinCashPayRule getCashPayRule() {
		return cashPayRule;
	}

	public void setCashPayRule(FinCashPayRule cashPayRule) {
		this.cashPayRule = cashPayRule;
	}

	public List<FinCashPlan> getCashPlanList() {
		return cashPlanList;
	}

	public void setCashPlanList(List<FinCashPlan> cashPlanList) {
		this.cashPlanList = cashPlanList;
	}

}
