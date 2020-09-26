/*
 * 文件名：BankAccountTradeProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeReaderDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeWriterDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.service.BankAccountTradeProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 
 * 银行账交易账务批处理-processor
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BankAccountTradeProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankAccountTradeProcessor extends BaseProcessor<NeedOperateEntity, BankAccountTradeWriterDto> {

	@Autowired
	private BankAccountTradeProcessorService bankAccountTradeProcessorService;

	@Override
	public BankAccountTradeWriterDto process(NeedOperateEntity items) throws Exception {
		// 获取reader方法的返回值
		BankAccountTradeReaderDto acctEntity = (BankAccountTradeReaderDto) items;

		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());
		// 将返回值塞入参数中
		thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

		// 调用service方法进行逻辑逻辑
		BankAccountTradeWriterDto result = bankAccountTradeProcessorService.execute(thisParams);

		// 当为null时候，给予初始化
		if (result == null) {
			result = new BankAccountTradeWriterDto();
		}

		result.setProdId(acctEntity.getBooksetId());

		return result;
	}

}
