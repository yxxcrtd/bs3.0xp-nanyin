package com.joyintech.tams.model.impairment.batch.eadcalculation.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.model.impairment.batch.eadcalculation.listener.EadCalCulationBatchListener;
import com.joyintech.tams.model.impairment.batch.eadcalculation.processor.EadCalCulationBatchProcessor;
import com.joyintech.tams.model.impairment.batch.eadcalculation.processor.EadCalculationDelProcessor;
import com.joyintech.tams.model.impairment.batch.eadcalculation.reader.EadCalCulationDelReader;
import com.joyintech.tams.model.impairment.batch.eadcalculation.reader.EadCalculationBatchReader;
import com.joyintech.tams.model.impairment.batch.eadcalculation.writer.EadCalCulationBatchWriter;
import com.joyintech.tams.model.impairment.batch.eadcalculation.writer.EadCalculationDelWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * EAD计算执行入口
 * @author  zhangfei
 * @date 2021年7月16日15:59:13
 */
@Service("eadCalCulationBatchTask")
@Scope("prototype")
public class EadCalCulationBatchTask extends BatchBaseService {

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
     * step0Reader
     */
    @Autowired
    private EadCalCulationDelReader step0Reader;

    /**
     * step0Processor
     */
    @Autowired
    private EadCalculationDelProcessor step0Processor;

    /**
     * step0Writer
     */
    @Autowired
    private EadCalculationDelWriter step0Writer;
    /**
     * step1Reader
     */
    @Autowired
    private EadCalculationBatchReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private EadCalCulationBatchProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private EadCalCulationBatchWriter step1Writer;

    @Autowired
    private EadCalCulationBatchListener eadCalCulationBatchListener;

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
        params.put("BATCHTASK", this.getBatchTask());
        params.put("PLATE_DATE", batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        step0Reader.setParams(params);
        step0Processor.setParams(params);
        step0Writer.setParams(params);
        logger.info("批处理[" + this.getBatchTask().getTaskName() + "]开始。");
        return batchBuilder.init(this)
                .batch("step0", (BaseReader)step0Reader,(BaseProcessor)step0Processor, (BaseWriter)step0Writer)
                .batch("step1", (BaseReader)step1Reader,(BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId()
                ,eadCalCulationBatchListener).run();

    }
}
