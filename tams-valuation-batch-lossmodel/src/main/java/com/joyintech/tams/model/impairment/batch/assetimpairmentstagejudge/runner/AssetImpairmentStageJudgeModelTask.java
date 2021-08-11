package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.listener.AssetImpairmentStageJudgeModelListener;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.processor.AssetImpairmentStageJudgeModelDelProcessor;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.processor.AssetImpairmentStageJudgeModelProcessor;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.reader.AssetImpairmentStageJudgeModelDelReader;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.reader.AssetImpairmentStageJudgeModelReader;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.writer.AssetImpairmentStageJudgeModelDelWriter;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.writer.AssetImpairmentStageJudgeModelWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("assetImpairmentStageJudgeModelTask")
@Scope("prototype")
public class AssetImpairmentStageJudgeModelTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * 读
     */
    @Autowired
    private AssetImpairmentStageJudgeModelReader modelReader;
    @Autowired
    private AssetImpairmentStageJudgeModelDelReader modelDelReader;
    /**
     * 执行
     */
    @Autowired
    private AssetImpairmentStageJudgeModelProcessor modelProcessor;
    @Autowired
    private AssetImpairmentStageJudgeModelDelProcessor modelDelProcessor;
    /**
     * 写
     */
    @Autowired
    private AssetImpairmentStageJudgeModelWriter modelWriter;
    @Autowired
    private AssetImpairmentStageJudgeModelDelWriter modelDelWriter;
    @Autowired
    private AssetImpairmentStageJudgeModelListener assetImpairmentStageJudgeModelListener;

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

        //把执行日期当作平台日来替换处理
        params.put("PLATE_DATE", batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());

        // 各步骤设置参数
        modelReader.setParams(params);
        modelProcessor.setParams(params);
        modelWriter.setParams(params);
        modelDelReader.setParams(params);
        modelDelProcessor.setParams(params);
        modelDelWriter.setParams(params);

        JobExecution jobExecution = batchBuilder.init(this).batch("step0",(BaseReader)modelDelReader,(BaseProcessor)modelDelProcessor,(BaseWriter)modelDelWriter)
                .batch("step1",(BaseReader)modelReader,(BaseProcessor)modelProcessor, (BaseWriter)modelWriter).builder(
                this.getBatchTask().getBeanName()  + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId()
                , assetImpairmentStageJudgeModelListener).run();
       return jobExecution;
    }
}
