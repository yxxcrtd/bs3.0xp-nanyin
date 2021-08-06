package com.joyintech.tams.model.impairment.batch.eclcalculation.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.eclcalculation.listener.EclCalculationListener;
import com.joyintech.tams.model.impairment.batch.eclcalculation.processor.EclCalculationDelProcessor;
import com.joyintech.tams.model.impairment.batch.eclcalculation.processor.EclCalculationProcessor;
import com.joyintech.tams.model.impairment.batch.eclcalculation.reader.EclCalculationDelReader;
import com.joyintech.tams.model.impairment.batch.eclcalculation.reader.EclCalculationReader;
import com.joyintech.tams.model.impairment.batch.eclcalculation.writer.EclCalculationDelWriter;
import com.joyintech.tams.model.impairment.batch.eclcalculation.writer.EclCalculationWriter;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.listener.ImpairmentDataLoadListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 减值模型-ECL计算批处理
 */
@Service("eclCalculationTask")
@Scope("prototype")
public class EclCalculationTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Boolean isRunning = false;
    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * 读
     */
    @Autowired
    private EclCalculationReader eclCalculationReader;
    /**
     * 执行
     */
    @Autowired
    private EclCalculationProcessor eclCalculationProcessor;
    /**
     * 写
     */
    @Autowired
    private EclCalculationWriter eclCalculationWriter;
    /**
     * 读
     */
    @Autowired
    private EclCalculationDelReader eclCalculationDelReader;
    /**
     * 执行
     */
    @Autowired
    private EclCalculationDelProcessor eclCalculationDelProcessor;
    /**
     * 写
     */
    @Autowired
    private EclCalculationDelWriter eclCalculationDelWriter;

    @Autowired
    private EclCalculationListener eclCalculationListener;

    @Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put("BATCH_INFO", batchExecuteInfo);

//        把执行日期当作平台日来替换处理
        params.put("PLATE_DATE", batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        eclCalculationReader.setParams(params);
        eclCalculationProcessor.setParams(params);
        eclCalculationWriter.setParams(params);
        eclCalculationDelReader.setParams(params);
        eclCalculationDelProcessor.setParams(params);
        eclCalculationDelWriter.setParams(params);

        return batchBuilder.init(this)
                .batch("step0", (BaseReader)eclCalculationDelReader,(BaseProcessor)eclCalculationDelProcessor, (BaseWriter)eclCalculationDelWriter)
                .batch("step1", (BaseReader)eclCalculationReader,(BaseProcessor)eclCalculationProcessor, (BaseWriter)eclCalculationWriter)
                .builder(this.getBatchTask().getBeanName()  + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId()
                        , eclCalculationListener).run();
    }
}
