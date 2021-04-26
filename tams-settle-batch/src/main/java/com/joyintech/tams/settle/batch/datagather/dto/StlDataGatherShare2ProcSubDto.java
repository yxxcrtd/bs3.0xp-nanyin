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
 * 股票除权、现金分红、送转交易的Process处理用SubDTO
 * @author 陈卓
 * @date 2020年07月24日
 * @see StlDataGatherShare2ProcSubDto
 * @since 1.0
 */
public class StlDataGatherShare2ProcSubDto {
	
	/**
	 * 除权交易DTO LIST
	 */
	private SettleDelDealDetailDto settleDelDealDetailDto;
	
	/**
	 * 现金分红成交数据明细DTO LIST
	 */
	private List<SettleTransDataDto> settleTransDataDtoListFh;
	
	/**
	 * 送转成交数据明细DTO LIST
	 */
	private List<SettleTransDataDto> settleTransDataDtoListSz;

	public SettleDelDealDetailDto getSettleDelDealDetailDto() {
		return settleDelDealDetailDto;
	}

	public void setSettleDelDealDetailDto(SettleDelDealDetailDto settleDelDealDetailDto) {
		this.settleDelDealDetailDto = settleDelDealDetailDto;
	}

	public List<SettleTransDataDto> getSettleTransDataDtoListFh() {
		return settleTransDataDtoListFh;
	}

	public void setSettleTransDataDtoListFh(List<SettleTransDataDto> settleTransDataDtoListFh) {
		this.settleTransDataDtoListFh = settleTransDataDtoListFh;
	}

	public List<SettleTransDataDto> getSettleTransDataDtoListSz() {
		return settleTransDataDtoListSz;
	}

	public void setSettleTransDataDtoListSz(List<SettleTransDataDto> settleTransDataDtoListSz) {
		this.settleTransDataDtoListSz = settleTransDataDtoListSz;
	}
}
