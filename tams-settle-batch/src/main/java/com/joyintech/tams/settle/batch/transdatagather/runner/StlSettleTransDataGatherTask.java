package com.joyintech.tams.settle.batch.transdatagather.runner;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.settle.batch.transdatagather.processor.StlSettleTransDataGatherProcessor;
import com.joyintech.tams.settle.batch.transdatagather.reader.StlSettleTransDataGatherReader;
import com.joyintech.tams.settle.batch.transdatagather.writer.StlSettleTransDataGatherWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 日终清结算-成交明细汇总
 * @author meifaxin
 * @date 2021/10/29
 * @see StlSettleTransDataGatherTask
 * @since 1.0
 */
@Service("stlSettleTransDataGatherTask")
@Lazy
public class StlSettleTransDataGatherTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<StlSettleTransDataGatherReader, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private StlSettleTransDataGatherReader stlSettleTransDataGatherReader;

    @Autowired
    private StlSettleTransDataGatherProcessor stlSettleTransDataGatherProcessor;

    @Autowired
    private StlSettleTransDataGatherWriter stlSettleTransDataGatherWriter;

    @Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        return batchBuilder.init(this)
                .batch("step1", stlSettleTransDataGatherReader, stlSettleTransDataGatherProcessor, stlSettleTransDataGatherWriter)
                .builder("成交明细汇总").run();
    }
}
