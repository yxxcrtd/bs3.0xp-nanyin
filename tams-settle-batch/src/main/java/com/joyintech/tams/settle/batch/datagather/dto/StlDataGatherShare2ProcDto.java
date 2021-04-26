/*
 * 文件名：TrdProductDealDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年07月24日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.settle.batch.datagather.dto;

import java.util.List;

/**
 * 股票除权、现金分红、送转交易的Process处理用DTO
 * @author 陈卓
 * @date 2020年07月24日
 * @see StlDataGatherShare2ProcDto
 * @since 1.0
 */
public class StlDataGatherShare2ProcDto {
	
	/**
	 * 汇总交易DTO
	 */
	private TotalTrdProductDealDto totalTrdProductDealDto;
	
	/**
	 * StlDataGatherShare2ProcSubDto LIST
	 */
	private List<StlDataGatherShare2ProcSubDto> stlDataGatherShare2ProcSubDtoList;

	public TotalTrdProductDealDto getTotalTrdProductDealDto() {
		return totalTrdProductDealDto;
	}

	public void setTotalTrdProductDealDto(TotalTrdProductDealDto totalTrdProductDealDto) {
		this.totalTrdProductDealDto = totalTrdProductDealDto;
	}

	public List<StlDataGatherShare2ProcSubDto> getStlDataGatherShare2ProcSubDtoList() {
		return stlDataGatherShare2ProcSubDtoList;
	}

	public void setStlDataGatherShare2ProcSubDtoList(
			List<StlDataGatherShare2ProcSubDto> stlDataGatherShare2ProcSubDtoList) {
		this.stlDataGatherShare2ProcSubDtoList = stlDataGatherShare2ProcSubDtoList;
	}
}
