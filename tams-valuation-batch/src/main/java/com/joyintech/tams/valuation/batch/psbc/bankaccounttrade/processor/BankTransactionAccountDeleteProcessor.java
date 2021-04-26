/*
 * 文件名：BankAccountTradeProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankaccounttrade.processor;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeReaderDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeWriterDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BokVouchInfoDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 银行账交易账务批处理-processor
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BankAccountTradeDeleteProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankTransactionAccountDeleteProcessor extends BaseProcessor<NeedOperateEntity, BankAccountTradeWriterDto> {

	@Override
	public BankAccountTradeWriterDto process(NeedOperateEntity items) throws Exception {
		// 获取reader方法的返回值
		BankAccountTradeReaderDto acctEntity = (BankAccountTradeReaderDto) items;

		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());
		// 将返回值塞入参数中
		thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

		// 调用service方法进行逻辑逻辑
		BankAccountTradeWriterDto result = new BankAccountTradeWriterDto();
		List<BokVouchInfoDto> listBokVouchInfoDto = Lists.newArrayList();
		BokVouchInfoDto bokVouchInfoDto = new BokVouchInfoDto();
		bokVouchInfoDto.setBooksetId(acctEntity.getBooksetId());
		bokVouchInfoDto.setBookDate(acctEntity.getBookDate());
		listBokVouchInfoDto.add(bokVouchInfoDto);
		result.setListBokVouchInfoDto(listBokVouchInfoDto);

		result.setProdId(acctEntity.getBooksetId());

		return result;
	}

}
