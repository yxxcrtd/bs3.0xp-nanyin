/*
 * 文件名：BussFinProductOpenPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.maintenance.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenRule;

// @ValidLogic(targetClass = BussFinProductOpenPlanValidator.class)
public class BussFinProductOpenPlanDto2 extends FinOpenRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hdayOpenFlag;
	private String sameFLag;
	private String dayOfMonth;
	private String openDateRadio;
	/**
	 * 开放计划列表
	 */
	private transient List<FinOpenPlan> finOpenPlanList;

	public String getHdayOpenFlag() {
		return hdayOpenFlag;
	}

	public void setHdayOpenFlag(String hdayOpenFlag) {
		this.hdayOpenFlag = hdayOpenFlag;
	}

	public String getSameFLag() {
		return sameFLag;
	}

	public void setSameFLag(String sameFLag) {
		this.sameFLag = sameFLag;
	}

	public List<FinOpenPlan> getFinOpenPlanList() {
		return finOpenPlanList;
	}

	public void setFinOpenPlanList(List<FinOpenPlan> finOpenPlanList) {
		this.finOpenPlanList = finOpenPlanList;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getOpenDateRadio() {
		return openDateRadio;
	}

	public void setOpenDateRadio(String openDateRadio) {
		this.openDateRadio = openDateRadio;
	}
}
