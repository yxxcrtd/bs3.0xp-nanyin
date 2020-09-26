package com.joyintech.tams.valuation.batch.positionmake.runner;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.positionmake.processor.PositionMakeProcessor;
import com.joyintech.tams.valuation.batch.positionmake.reader.PositionMakeReader;
import com.joyintech.tams.valuation.batch.positionmake.writer.PositionMakeWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 头寸生成批处理
 * 
 * @author Administrator
 *
 */
@Service("positionMakeTask")
@Scope("prototype")
public class PositionMakeTask extends BatchBaseService {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * batchBuilder
	 */
	@Autowired
	private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

	/**
	 * step1Reader
	 */
	@Autowired
	private PositionMakeReader step1Reader;

	/**
	 * step1Processor
	 */
	@Autowired
	private PositionMakeProcessor step1Processor;

	/**
	 * step1Writer
	 */
	@Autowired
	private PositionMakeWriter step1Writer;

	@Autowired
	private BookCommonLogic bookCommonLogic;

	/**
	 * 批处理入口
	 */
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
		params.put("BATCHINFO", batchExecuteInfo);// 待处理列表list
		Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
		params.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
		params.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
		params.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
		params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
		params.put("taskId", this.getBatchExecuteInfo().getTaskId());
		params.put(GlobalConstant.BOOKSETS, this.getProductIdList());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
		
		// 各步骤设置参数
		step1Reader.setParams(params);
		step1Processor.setParams(params);
		step1Writer.setParams(params);

		return batchBuilder.init(this)
				.batch("step1", (BaseReader) step1Reader, (BaseProcessor) step1Processor, (BaseWriter) step1Writer)
				.builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();

	}

}
