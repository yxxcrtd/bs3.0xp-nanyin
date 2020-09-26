/*
 * 文件名：BankAccountTradeWriterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 
 * 银行账交易账务批处理-writer
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BankAccountTradeWriterDto
 * @since 1.0
 */
public class BankAccountTradeWriterDto extends NeedToWriteEntity {

	private List<BokVouchInfoDto> listBokVouchInfoDto = new ArrayList<BokVouchInfoDto>();

	public List<BokVouchInfoDto> getListBokVouchInfoDto() {
		return listBokVouchInfoDto;
	}

	public void setListBokVouchInfoDto(List<BokVouchInfoDto> listBokVouchInfoDto) {
		this.listBokVouchInfoDto = listBokVouchInfoDto;
	}

	

}
