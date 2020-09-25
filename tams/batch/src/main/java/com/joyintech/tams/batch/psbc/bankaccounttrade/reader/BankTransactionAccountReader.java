/*
 * 文件名：BankAccountTradeReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.bankaccounttrade.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.psbc.bankaccounttrade.service.BankTransactionAccountReaderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeReaderDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.reader.BankAccountTradeReader;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 
 * 银行账交易账务批处理-reader
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月10日
 * @see BankAccountTradeReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankTransactionAccountReader extends BaseReader<BankAccountTradeReaderDto> {

	@Autowired
	private BookCommonLogic bookCommonLogic;

	@Autowired
	private BankTransactionAccountReaderService bankAccountTradeReaderService;

	@Override
	public List<BankAccountTradeReaderDto> createList() {
		// 变量定义
		List<BankAccountTradeReaderDto> listBankAccountTradeReaderDto = new ArrayList<BankAccountTradeReaderDto>();

		Map<String, Object> serviceParamMap = new HashMap<String, Object>();
		serviceParamMap.put("BATCHINFO", this.getBatchExecuteInfo());
		serviceParamMap.put("BATCHDEL", this.getBatchExecuteDetail());
		serviceParamMap.put("BATCHTASK", this.getBatchTask());
		Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
		serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
		serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
		serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
		serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
		serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
		serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));

		serviceParamMap.put("matchType", DictCodeListExt1.匹配类型_任务代码_业务类型.getValue());
		serviceParamMap.put("finprodType1", DictCodeListExt1.金融产品类型_理财产品.getValue());
		serviceParamMap.put("finprodType2", DictCodeListExt1.金融产品类型_固定管理费.getValue());
		serviceParamMap.put("finprodType3", DictCodeListExt1.金融产品类型_浮动管理费.getValue());
		serviceParamMap.put("profitFlag", DictCodeListExt1.收益标识_非保本浮动收益型.getValue());

		if (this.getParams() != null) {
			this.getParams().putAll(serviceParamMap);
		}
		// 获取reader返回值
		listBankAccountTradeReaderDto = bankAccountTradeReaderService.execute(serviceParamMap);

		// 返回处理结果
		return listBankAccountTradeReaderDto;
	}

}
