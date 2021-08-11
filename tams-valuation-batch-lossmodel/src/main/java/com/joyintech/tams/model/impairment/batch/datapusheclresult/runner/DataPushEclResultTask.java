package com.joyintech.tams.model.impairment.batch.datapusheclresult.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.listener.DataPushEclResultListener;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.processor.DataPushEclResultProcessor;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.reader.DataPushEclResultReader;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.writer.DataPushEclResultWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:数据推送批处理Task
 * @auther: yuzj
 * @date: 2021/7/20
 * @param:
 * @return:
 */
@Service("dataPushEclResultTask")
@Scope("prototype")
public class DataPushEclResultTask extends BatchBaseService{

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
    private DataPushEclResultListener dataPushEclResultListener;

    /**
     * 数据推送批处理Reader
     */
    @Autowired
    private DataPushEclResultReader step1Reader;

    /**
     * 数据推送批处理Processor
     */
    @Autowired
    private DataPushEclResultProcessor step1Processor;

    /**
     * 数据推送批处理Writer
     */
    @Autowired
    private DataPushEclResultWriter step1Writer;


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public JobExecution execute()
        throws BatchException {

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
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                    this.getBatchTask().getBeanName() + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId(),dataPushEclResultListener).run();
    }
    
}
