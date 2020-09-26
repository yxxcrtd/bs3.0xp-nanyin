/*
 * 文件名：BankAccountTradeTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.runner;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.valuation.batch.bankaccounttrade.processor.BankAccountTradeDeleteProcessor;
import com.joyintech.tams.valuation.batch.bankaccounttrade.reader.BankAccountTradeDeleteReader;
import com.joyintech.tams.valuation.batch.bankaccounttrade.writer.BankAccountTradeDeleteWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.valuation.batch.bankaccounttrade.processor.BankAccountTradeProcessor;
import com.joyintech.tams.valuation.batch.bankaccounttrade.reader.BankAccountTradeReader;
import com.joyintech.tams.valuation.batch.bankaccounttrade.writer.BankAccountTradeWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;

/**
 * 
 * 银行账交易账务批处理
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月12日
 * @see BankAccountTradeTask
 * @since 1.0
 */
@Service("bankAccountTradeTask")
@Scope("prototype")
public class BankAccountTradeTask extends BatchBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * BatchBuilder
	 */
	@Autowired
	private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

	@Autowired
	private BankAccountTradeDeleteReader step1Reader;

	@Autowired
	private BankAccountTradeDeleteProcessor step1Processor;

	@Autowired
	private BankAccountTradeDeleteWriter step1Writer;

	/**
	 * step2Reader
	 */
	@Autowired
	private BankAccountTradeReader step2Reader;

	/**
	 * step2Processor
	 */
	@Autowired
	private BankAccountTradeProcessor step2Processor;

	/**
	 * step2Writer
	 */
	@Autowired
	private BankAccountTradeWriter step2Writer;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JobExecution execute() throws BatchException {

		// 获取批处理信息
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
		if (batchExecuteInfo == null) {
			logger.info("无启动参数，批处理退出。");
			return null;
		}

		// 设置此task的序列任务公用的params参数
		Map<String, Object> params = new HashMap<>();

		// 各步骤设置参数
		step1Reader.setParams(params);
		step1Processor.setParams(params);
		step2Reader.setParams(params);
		step2Processor.setParams(params);
		step1Writer.setParams(params);
		step2Writer.setParams(params);

		return batchBuilder.init(this)
				.batch("step1", (BaseReader) step1Reader, (BaseProcessor) step1Processor, (BaseWriter) step1Writer)
				.batch("step2", (BaseReader) step2Reader, (BaseProcessor) step2Processor, (BaseWriter) step2Writer)
				.builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
	}

}
