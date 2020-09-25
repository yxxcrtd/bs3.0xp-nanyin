/*
 * 文件名：PlanningStrategyPoolDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClass;

/**
 * 策略明细
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月17日
 * @see PlanningStrategyPoolDetailDto
 * @since 1.0
 */
public class PlanningStrategyPoolDetailDto extends PtlInvStrategyDetailClass {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 比例
	 */
	private BigDecimal ratio;

	/**
	 * 获取 ratio
	 * 
	 * @return ratio.
	 */
	public BigDecimal getRatio() {
		return ratio;
	}

	/**
	 * 设置 ratio
	 * 
	 * @param ratio
	 *            ratio
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

}
