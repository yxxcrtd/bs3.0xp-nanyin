/*
 * 文件名：PrdIssueScheduleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.scheduling.dto;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;

/**
 * 理财产品排期管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月5日
 * @see PrdIssueScheduleDto
 * @since 1.0
 */
public class PrdIssueScheduleDto extends PaPrdIssueSchedule{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 5546351764295855260L;
	
	/**
	 * 一万
	 */
	public static final BigDecimal tenTou = new BigDecimal(10000);
	
	/**
	 * 一百
	 */
	public static final BigDecimal hundrend = new BigDecimal(100);
	
	/**
	 * {@linkplain}
	 */
	@Valid
	private BussRepFinProductInfoDto bussRepFinProductInfoDto;

	/**
	 * @return the bussRepFinProductInfoDto
	 */
	public BussRepFinProductInfoDto getBussRepFinProductInfoDto() {
		return bussRepFinProductInfoDto;
	}

	/**
	 * @param bussRepFinProductInfoDto the bussRepFinProductInfoDto to set
	 */
	public void setBussRepFinProductInfoDto(BussRepFinProductInfoDto bussRepFinProductInfoDto) {
		this.bussRepFinProductInfoDto = bussRepFinProductInfoDto;
	}

}
