/*
 * 文件名：BankAccountTradeWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.bankaccounttrade.writer;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.batch.psbc.bankaccounttrade.dao.BankTransactionAccountMapper;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dao.BankAccountTradeMapper;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeWriterDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 
 * 银行账交易账务批处理-writer
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BankAccountTradeDeleteWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankTransactionAccountDeleteWriter extends BaseWriter<NeedToWriteEntity> {

	@Autowired
	private BankTransactionAccountMapper bankAccountTradeMapper;

	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值 - 记账凭证list
		@SuppressWarnings("unchecked")
		List<BankAccountTradeWriterDto> resultList = (List<BankAccountTradeWriterDto>) items;

		String booksetId = resultList.get(0).getListBokVouchInfoDto().get(0).getBooksetId();
		Date bookDate = resultList.get(0).getListBokVouchInfoDto().get(0).getBookDate();
		// 先删除该账套下所有凭证
		List<String> listVouNum = bankAccountTradeMapper.selectVouchNum(booksetId, bookDate);
		if (CollectionUtils.isNotEmpty(listVouNum)) {
			bankAccountTradeMapper.deleteBokVouchDetail(listVouNum);
			bankAccountTradeMapper.deleteBokVouchMain(listVouNum);
		}

	}

}
