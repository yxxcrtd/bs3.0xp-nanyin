/*
 * 文件名：BankAccountTradeReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.bankaccounttrade.dao.BankAccountTradeMapper;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeReaderDto;

/**
 * 
 * 银行账交易账务批处理-reader
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月10日
 * @see BankAccountTradeReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankAccountTradeReaderService {

	@Autowired
	private BankAccountTradeMapper bankAccountTradeMapper;

	public List<BankAccountTradeReaderDto> execute(Map<String, Object> param) {
		// 定义查询参数
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.putAll(param);
		List<BankAccountTradeReaderDto> listBankAccountTradeReaderDto = bankAccountTradeMapper
				.selectBankAccountTrade(sqlMap);
		return listBankAccountTradeReaderDto;
	}

}
