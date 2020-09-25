/*
 * 文件名：ResetBaseRuleInfoReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdmdateadjust.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * @version 1.0 2019年9月24日
 * 投资周期Entity
 */
public class InvestmentCycleEntityDto extends NeedOperateEntity{

	private String investmentCycle; // 投资周期
	
    private List<SusPrdMdateAdjustDto> susprdmdateadjustdtoList; // 子产品信息列表
    
	public String getInvestmentCycle() {
		return investmentCycle;
	}
	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}
	public List<SusPrdMdateAdjustDto> getSusprdmdateadjustdtoList() {
		return susprdmdateadjustdtoList;
	}
	public void setSusprdmdateadjustdtoList(List<SusPrdMdateAdjustDto> susprdmdateadjustdtoList) {
		this.susprdmdateadjustdtoList = susprdmdateadjustdtoList;
	}
}
