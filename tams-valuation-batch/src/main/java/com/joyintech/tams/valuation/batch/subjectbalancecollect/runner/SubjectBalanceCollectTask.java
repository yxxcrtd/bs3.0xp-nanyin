/*
 * 文件名：SubjectBalanceTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.subjectbalancecollect.runner;

import java.util.HashMap;
import java.util.Map;

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
import com.joyintech.tams.valuation.batch.subjectbalancecollect.processor.SubjectBalanceCollectDelProcessor;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.processor.SubjectBalanceCollectProcessor;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.reader.SubjectBalanceCollectDelReader;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.reader.SubjectBalanceCollectReader;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.writer.SubjectBalanceCollectDelWriter;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.writer.SubjectBalanceCollectWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 科目余额汇总批处理Runner
 * @author weipeng
 * @version 1.0 2018年5月30日
 * @see SubjectBalanceCollectTask
 * @since 1.0
 */
@Service("subjectBalanceCollectTask")
@Scope("prototype")
public class SubjectBalanceCollectTask extends BatchBaseService {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, 
                                                                    BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * step0Reader
     */
    @Autowired
    private SubjectBalanceCollectDelReader step0Reader;

    /**
     * step0Processor
     */
    @Autowired
    private SubjectBalanceCollectDelProcessor step0Processor;

    /**
     * step0Writer
     */
    @Autowired
    private SubjectBalanceCollectDelWriter step0Writer;
    
    /**
     * step1Reader
     */
    @Autowired
    private SubjectBalanceCollectReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private SubjectBalanceCollectProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private SubjectBalanceCollectWriter step1Writer;
    
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public JobExecution execute()
        throws BatchException {
        // 获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        params.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
		params.put("taskId", this.getBatchExecuteInfo().getTaskId());
		params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        // 各步骤设置参数
		step0Reader.setParams(params);
		step0Processor.setParams(params);
		step0Writer.setParams(params);
		step1Reader.setParams(params);
		step1Processor.setParams(params);
		step1Writer.setParams(params);
        logger.info("批处理[" + this.getBatchTask().getTaskName() + "]开始。");
        return batchBuilder.init(this).batch("step0", (BaseReader)step0Reader,
            (BaseProcessor)step0Processor, (BaseWriter)step0Writer).batch("step1",
                (BaseReader)step1Reader, (BaseProcessor)step1Processor,
                (BaseWriter)step1Writer).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
