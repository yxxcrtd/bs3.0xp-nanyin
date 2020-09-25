/*
 * 文件名：BussFinProductInvestPeriodDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

public class BussFinProductInvestPeriodDto extends BaseEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 3014041335382504274L;

	private String sn;
	
	private Integer investDays;
	
	private List<BussFinProductInvestPeriodDto> bussFinProductInvestPeriodDtoList;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getInvestDays() {
		return investDays;
	}

	public void setInvestDays(Integer investDays) {
		this.investDays = investDays;
	}

	public List<BussFinProductInvestPeriodDto> getBussFinProductInvestPeriodDtoList() {
		return bussFinProductInvestPeriodDtoList;
	}

	public void setBussFinProductInvestPeriodDtoList(
			List<BussFinProductInvestPeriodDto> bussFinProductInvestPeriodDtoList) {
		this.bussFinProductInvestPeriodDtoList = bussFinProductInvestPeriodDtoList;
	}
	
	
}
