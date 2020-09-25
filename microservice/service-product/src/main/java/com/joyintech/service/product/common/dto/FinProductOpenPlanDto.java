/*
 * 文件名：FinProductOpenPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenRule;

/**
 * 产品开放计划
 * 
 * @author 张亮
 * @version 1.0 2018年9月29日
 * @see FinProductOpenPlanDto
 * @since 1.0
 */
public class FinProductOpenPlanDto {
	
	/**
	 * {@linkplain}
	 */
	private FinOpenRule finOpenRule;
	
	/**
	 * {@linkplain}
	 */
	private List<FinOpenPlan>  finOpenPlanList;


	/**
	 * @return the finOpenRule
	 */
	public FinOpenRule getFinOpenRule() {
		return finOpenRule;
	}

	/**
	 * @param finOpenRule the finOpenRule to set
	 */
	public void setFinOpenRule(FinOpenRule finOpenRule) {
		this.finOpenRule = finOpenRule;
	}

	/**
	 * @return the finOpenPlanList
	 */
	public List<FinOpenPlan> getFinOpenPlanList() {
		return finOpenPlanList;
	}

	/**
	 * @param finOpenPlanList the finOpenPlanList to set
	 */
	public void setFinOpenPlanList(List<FinOpenPlan> finOpenPlanList) {
		this.finOpenPlanList = finOpenPlanList;
	}
	
}
