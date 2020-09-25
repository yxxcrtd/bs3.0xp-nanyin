package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.runner;

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
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.processor.DisReportAssetIndexDelProcessor;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.processor.DisReportAssetIndexProcessor;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.reader.DisReportAssetIndexDelReader;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.reader.DisReportAssetIndexReader;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.writer.DisReportAssetIndexDelWriter;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.writer.DisReportAssetIndexWriter;

@Service(value = "disReportAssetIndexRunner")
@Scope("prototype")
public class DisReportAssetIndexRunner  extends BatchBaseService{
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
    private DisReportAssetIndexDelReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private DisReportAssetIndexDelProcessor step1Processor;
    
    /**
     * step1Writer
     */
    @Autowired
    private DisReportAssetIndexDelWriter step1Writer;
    
    /**
     * step1Reader
     */
    @Autowired
    private DisReportAssetIndexReader step2Reader;

    /**
     * step1Processor
     */
    @Autowired
    private DisReportAssetIndexProcessor step2Processor;

    /**
     * step1Writer
     */
    @Autowired
    private DisReportAssetIndexWriter step2Writer;
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public synchronized JobExecution execute() throws BatchException {

        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();

        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        logger.info("资产指标表批处理开始执行");
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
