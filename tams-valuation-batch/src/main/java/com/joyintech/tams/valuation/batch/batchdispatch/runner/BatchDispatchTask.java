/*
 * 文件名：BatchDispatchTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.valuation.batch.batchdispatch.runner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.framework.util.DateUtil;
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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.valuation.batch.batchdispatch.processor.BatchDispatchProcessor;
import com.joyintech.tams.valuation.batch.batchdispatch.processor.BatchDispatchProcessorStep0;
import com.joyintech.tams.valuation.batch.batchdispatch.processor.BatchDispatchProcessorStep2;
import com.joyintech.tams.valuation.batch.batchdispatch.processor.BatchDispatchProcessorStep3;
import com.joyintech.tams.valuation.batch.batchdispatch.reader.BatchDispatchReader;
import com.joyintech.tams.valuation.batch.batchdispatch.reader.BatchDispatchReaderStep0;
import com.joyintech.tams.valuation.batch.batchdispatch.reader.BatchDispatchReaderStep2;
import com.joyintech.tams.valuation.batch.batchdispatch.reader.BatchDispatchReaderStep3;
import com.joyintech.tams.valuation.batch.batchdispatch.writer.BatchDispatchWriter;
import com.joyintech.tams.valuation.batch.batchdispatch.writer.BatchDispatchWriterStep0;
import com.joyintech.tams.valuation.batch.batchdispatch.writer.BatchDispatchWriterStep2;
import com.joyintech.tams.valuation.batch.batchdispatch.writer.BatchDispatchWriterStep3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DataHandleUtil;

/**
 * 整体任务调度批处理 生成新的批处理执行信息，并插入批处理队列
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchTask
 * @since 1.0
 */
@Service("batchDispatchTask")
@Scope("prototype")
public class BatchDispatchTask extends BatchBaseService {

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
     * batchDispatchReader
     */
    @Autowired
    private BatchDispatchReaderStep0 batchDispatchReaderStep0;
    
    /**
     * batchDispatchProcessor
     */
    @Autowired
    private BatchDispatchProcessorStep0 batchDispatchProcessorStep0;

    /**
     * batchDispatchWriter
     */
    @Autowired
    private BatchDispatchWriterStep0 batchDispatchWriterStep0;
    
    /**
     * batchDispatchReader
     */
    @Autowired
    private BatchDispatchReader batchDispatchReader;

    /**
     * batchDispatchProcessor
     */
    @Autowired
    private BatchDispatchProcessor batchDispatchProcessor;

    /**
     * batchDispatchWriter
     */
    @Autowired
    private BatchDispatchWriter batchDispatchWriter;

    /**
     * batchDispatchReader
     */
    @Autowired
    private BatchDispatchReaderStep2 batchDispatchReaderStep2;

    /**
     * batchDispatchProcessor
     */
    @Autowired
    private BatchDispatchProcessorStep2 batchDispatchProcessorStep2;

    /**
     * batchDispatchWriter
     */
    @Autowired
    private BatchDispatchWriterStep2 batchDispatchWriterStep2;

    /**
     * batchDispatchReader
     */
    @Autowired
    private BatchDispatchReaderStep3 batchDispatchReaderStep3;

    /**
     * batchDispatchProcessor
     */
    @Autowired
    private BatchDispatchProcessorStep3 batchDispatchProcessorStep3;

    /**
     * batchDispatchWriter
     */
    @Autowired
    private BatchDispatchWriterStep3 batchDispatchWriterStep3;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public JobExecution execute()
        throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.warn("无启动参数，批处理退出。");
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        Date oldDate = DateUtil.addDay(DateUtil.parse(DateUtil.getDate()),-3);
		params.put("oldDate", oldDate);
        // 任务组ID
        String detailSeqNo = IDGenerator.generate();
        params.put("detailSeqNo", detailSeqNo);
        List<List<String>> booksetLists =  DataHandleUtil.splitList(this.getProductIdList(), 1000);
        params.put(GlobalConstant.BOOKSETLISTS, booksetLists);
        batchDispatchReaderStep0.setParams(params);
        batchDispatchReader.setParams(params);
        batchDispatchReaderStep2.setParams(params);
        batchDispatchReaderStep3.setParams(params);
        batchDispatchProcessorStep0.setParams(params);
        batchDispatchProcessor.setParams(params);
        batchDispatchProcessorStep2.setParams(params);
        batchDispatchProcessorStep3.setParams(params);
        return batchBuilder.init(this)
            .batch("step0", (BaseReader)batchDispatchReaderStep0,
                (BaseProcessor)batchDispatchProcessorStep0,
                (BaseWriter)batchDispatchWriterStep0)
            .batch("step1", (BaseReader)batchDispatchReader,
                (BaseProcessor)batchDispatchProcessor,
                (BaseWriter)batchDispatchWriter)
            .batch("step2", (BaseReader)batchDispatchReaderStep2,
                (BaseProcessor)batchDispatchProcessorStep2,
                (BaseWriter)batchDispatchWriterStep2)
            .batch("step3", (BaseReader)batchDispatchReaderStep3,
                (BaseProcessor)batchDispatchProcessorStep3,
                (BaseWriter)batchDispatchWriterStep3)
            .builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
