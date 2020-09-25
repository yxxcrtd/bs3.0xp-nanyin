package com.joyintech.tams.invest.batch.tradorSeq.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.overduesecpositionforward.processor.OverdueSecPositionForwardDelProcessor;
import com.joyintech.tams.invest.batch.overduesecpositionforward.processor.OverdueSecPositionForwardProcessor;
import com.joyintech.tams.invest.batch.overduesecpositionforward.reader.OverdueSecPositionForwardDelReader;
import com.joyintech.tams.invest.batch.overduesecpositionforward.reader.OverdueSecPositionForwardReader;
import com.joyintech.tams.invest.batch.overduesecpositionforward.writer.OverdueSecPositionForwardDelWriter;
import com.joyintech.tams.invest.batch.overduesecpositionforward.writer.OverdueSecPositionForwardWriter;
import com.joyintech.tams.invest.batch.tradorSeq.processor.TradorSeqForwardDelProcessor;
import com.joyintech.tams.invest.batch.tradorSeq.processor.TradorSeqForwardProcessor;
import com.joyintech.tams.invest.batch.tradorSeq.reader.TradorSeqForwardDelReader;
import com.joyintech.tams.invest.batch.tradorSeq.reader.TradorSeqForwardReader;
import com.joyintech.tams.invest.batch.tradorSeq.writer.TradorSeqForwardDelWriter;
import com.joyintech.tams.invest.batch.tradorSeq.writer.TradorSeqForwardWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("tradorSeqForwardTask")
@Scope("prototype")
public class TradorSeqForwardTask extends BatchBaseService {
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
    private TradorSeqForwardReader tradorSeqForwardReader;
    /**
     * 删除读
     */
    @Autowired
    private TradorSeqForwardDelReader tradorSeqForwardDelReader;
    /**
     * 执行
     */
    @Autowired
    private TradorSeqForwardProcessor tradorSeqForwardProcessor;
    /**
     * 删除执行
     */
    @Autowired
    private TradorSeqForwardDelProcessor tradorSeqForwardDelProcessor ;
    /**
     * 写
     */
    @Autowired
    private TradorSeqForwardWriter tradorSeqForwardWriter;
    /**
     * 删除写
     */
    @Autowired
    private TradorSeqForwardDelWriter tradorSeqForwardDelWriter;
    @Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put(BatchConstant.BATCH_INFO, batchExecuteInfo);

//        把执行日期当作平台日来替换处理
        params.put(BatchConstant.PLATE_DATE, batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        tradorSeqForwardDelReader.setParams(params);
        tradorSeqForwardDelProcessor.setParams(params);
        tradorSeqForwardDelWriter.setParams(params);
        tradorSeqForwardReader.setParams(params);
        tradorSeqForwardProcessor.setParams(params);
        tradorSeqForwardWriter.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)tradorSeqForwardDelReader,
                (BaseProcessor)tradorSeqForwardProcessor, (BaseWriter)tradorSeqForwardDelWriter).batch("step2",
                (BaseReader)tradorSeqForwardReader,(BaseProcessor)tradorSeqForwardProcessor, (BaseWriter)tradorSeqForwardWriter).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
