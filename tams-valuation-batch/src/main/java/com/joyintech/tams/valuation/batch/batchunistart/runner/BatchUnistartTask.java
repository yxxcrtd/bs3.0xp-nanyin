package com.joyintech.tams.valuation.batch.batchunistart.runner;


import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.valuation.batch.batchunistart.processor.BatchUnistartProcessor;
import com.joyintech.tams.valuation.batch.batchunistart.reader.BatchUnistartReader;
import com.joyintech.tams.valuation.batch.batchunistart.writer.BatchUnistartWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("batchUnistartTask")
@Scope("prototype")
public class BatchUnistartTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private BatchUnistartReader step0Reader;

    @Autowired
    private BatchUnistartProcessor step0Processor;

    @Autowired
    private BatchUnistartWriter step0Writer;

    @Override
    public JobExecution execute()
            throws BatchException {

        // 获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        logger.info("批处理[" + this.getBatchTask().getTaskName() + "]开始。");
        return batchBuilder.init(this).batch("step0", (BaseReader)step0Reader,
                (BaseProcessor)step0Processor, (BaseWriter)step0Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
