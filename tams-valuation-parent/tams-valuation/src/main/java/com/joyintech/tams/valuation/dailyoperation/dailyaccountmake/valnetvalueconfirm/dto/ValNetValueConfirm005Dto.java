/*
 * 文件名：ValNetValueConfirm004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：hechuan
 * 修改时间：2019年9月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 净值走势图
 * @author jiangyunlong
 * @version 1.0 2019年11月12日
 * @see ValNetValueConfirm005Dto
 * @since 1.0
 */

public class ValNetValueConfirm005Dto {
    private Date vDate;//起息日（取产品起息日、参数查询开始日最大值）
    private BigDecimal  netUnitValue;//单位净值
	private BigDecimal  yieldTerm;//周期年化收益率
	private BigDecimal intRate;//业绩基准（计提利率）

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public BigDecimal getNetUnitValue() {
		return netUnitValue;
	}

	public void setNetUnitValue(BigDecimal netUnitValue) {
		this.netUnitValue = netUnitValue;
	}

	public BigDecimal getYieldTerm() {
		return yieldTerm==null? BigDecimal.ZERO:yieldTerm;
	}

	public void setYieldTerm(BigDecimal yieldTerm) {
		this.yieldTerm = yieldTerm;
	}

	public BigDecimal getIntRate() {
		return intRate==null? BigDecimal.ZERO:intRate;
	}

	public void setIntRate(BigDecimal intRate) {
		this.intRate = intRate;
	}
}
