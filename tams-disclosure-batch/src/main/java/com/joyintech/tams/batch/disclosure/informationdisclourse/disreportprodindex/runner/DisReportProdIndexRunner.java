package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.runner;

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
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.processor.DisReportProdIndexDelProcessor;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.processor.DisReportProdIndexProcessor;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.reader.DisReportProdIndexDelReader;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.reader.DisReportProdIndexReader;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.writer.DisReportProdIndexDelWriter;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.writer.DisReportProdIndexWriter;

@Service(value = "disReportProdIndexRunner")
@Scope("prototype")
public class DisReportProdIndexRunner  extends BatchBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    /**
     * step1Reader
     */
    @Autowired
    private DisReportProdIndexDelReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private DisReportProdIndexDelProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private DisReportProdIndexDelWriter step1Writer;
    
    
    /**
     * step1Reader
     */
    @Autowired
    private DisReportProdIndexReader step2Reader;

    /**
     * step1Processor
     */
    @Autowired
    private DisReportProdIndexProcessor step2Processor;

    /**
     * step1Writer
     */
    @Autowired
    private DisReportProdIndexWriter step2Writer;
    
    

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public synchronized JobExecution execute() throws BatchException {

        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();

        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        logger.info("产品指标表跑批开始执行");
        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put("BATCHINFO", batchExecuteInfo);
        params.put("BATCHDEL", this.getBatchExecuteDetail());
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        
        
        step2Reader.setParams(params);
        step2Processor.setParams(params);
        step2Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).batch("step2",
                (BaseReader)step2Reader, (BaseProcessor)step2Processor,
                (BaseWriter)step2Writer).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
