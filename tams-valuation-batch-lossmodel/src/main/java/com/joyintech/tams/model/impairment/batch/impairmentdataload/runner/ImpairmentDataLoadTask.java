package com.joyintech.tams.model.impairment.batch.impairmentdataload.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.listener.ImpairmentDataLoadListener;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.processor.ImpairmentDataLoadDelProcessor;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.processor.ImpairmentDataLoadProcessor;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.processor.ImpairmentDataLoadStep2Processor;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.reader.ImpairmentDataLoadDelReader;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.reader.ImpairmentDataLoadReader;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.reader.ImpairmentDataLoadStep2Reader;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.writer.ImpairmentDataLoadDelWriter;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.writer.ImpairmentDataLoadStep2Writer;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.writer.ImpairmentDataLoadWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 减值模型-数据加载批处理
 */
@Service("impairmentDataLoadTask")
@Scope("prototype")
public class ImpairmentDataLoadTask extends BatchBaseService {
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
    private ImpairmentDataLoadReader impairmentDataLoadReader;
    /**
     * 执行
     */
    @Autowired
    private ImpairmentDataLoadProcessor impairmentDataLoadProcessor;
    /**
     * 写
     */
    @Autowired
    private ImpairmentDataLoadWriter impairmentDataLoadWriter;

    /**
     * 读
     */
    @Autowired
    private ImpairmentDataLoadStep2Reader impairmentDataLoadStep2Reader;
    /**
     * 执行
     */
    @Autowired
    private ImpairmentDataLoadStep2Processor impairmentDataLoadStep2Processor;
    /**
     * 写
     */
    @Autowired
    private ImpairmentDataLoadStep2Writer impairmentDataLoadStep2Writer;
    /**
     * 读
     */
    @Autowired
    private ImpairmentDataLoadDelReader impairmentDataLoadDelReader;
    /**
     * 执行
     */
    @Autowired
    private ImpairmentDataLoadDelProcessor impairmentDataLoadDelProcessor;
    /**
     * 写
     */
    @Autowired
    private ImpairmentDataLoadDelWriter impairmentDataLoadDelWriter;

    @Autowired
    private ImpairmentDataLoadListener impairmentDataLoadListener;

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
        impairmentDataLoadReader.setParams(params);
        impairmentDataLoadProcessor.setParams(params);
        impairmentDataLoadWriter.setParams(params);
        impairmentDataLoadStep2Reader.setParams(params);
        impairmentDataLoadStep2Processor.setParams(params);
        impairmentDataLoadStep2Writer.setParams(params);
        impairmentDataLoadDelReader.setParams(params);
        impairmentDataLoadDelProcessor.setParams(params);
        impairmentDataLoadDelWriter.setParams(params);

        return batchBuilder.init(this)
                .batch("step0", (BaseReader)impairmentDataLoadDelReader,(BaseProcessor)impairmentDataLoadDelProcessor, (BaseWriter)impairmentDataLoadDelWriter)
                .batch("step1", (BaseReader)impairmentDataLoadReader,(BaseProcessor)impairmentDataLoadProcessor, (BaseWriter)impairmentDataLoadWriter)
                .batch("step2", (BaseReader)impairmentDataLoadStep2Reader,(BaseProcessor)impairmentDataLoadStep2Processor, (BaseWriter)impairmentDataLoadStep2Writer)
                .builder(this.getBatchTask().getBeanName()  + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId()
                        , impairmentDataLoadListener).run();
    }
}
