/*
 * 文件名：FinCashResetRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autocoupon.dto;

import com.joyintech.tams.common.base.entity.FinRateInfo;

/**
 * 利率重置规则
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2019年7月18日
 * @see FinCashResetRuleDto
 * @since 1.0
 */
public class FinCashResetRuleDto extends FinRateInfo {

	/**
	 * 重置频率 频率值
	 */
	private int resetFreqUnit;

	/**
	 * 获取 resetFreqUnit
	 * @return resetFreqUnit.
	 */
	public int getResetFreqUnit() {
		return resetFreqUnit;
	}

	/**
	 * 设置 resetFreqUnit
	 * @param resetFreqUnit resetFreqUnit
	 */
	public void setResetFreqUnit(int resetFreqUnit) {
		this.resetFreqUnit = resetFreqUnit;
	}


}
