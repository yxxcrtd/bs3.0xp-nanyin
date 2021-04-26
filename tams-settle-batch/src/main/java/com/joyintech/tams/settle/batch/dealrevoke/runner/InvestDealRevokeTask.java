package com.joyintech.tams.settle.batch.dealrevoke.runner;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.settle.batch.dealrevoke.processor.InvestDealRevokeProcessor;
import com.joyintech.tams.settle.batch.dealrevoke.reader.InvestDealRevokeReader;
import com.joyintech.tams.settle.batch.dealrevoke.writer.InvestDealRevokeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 投管交易撤销（投管交易汇总的前置批）
 * @author 何阳阳
 * @date 2020/8/3
 * @see InvestDealRevokeTask
 * @since 1.0
 */
@Service("investDealRevokeTask")
@Lazy
public class InvestDealRevokeTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<InvestDealRevokeReader, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private InvestDealRevokeReader investDealRevokeReader;

    @Autowired
    private InvestDealRevokeProcessor investDealRevokeProcessor;

    @Autowired
    private InvestDealRevokeWriter investDealRevokeWriter;

    @Override
    public JobExecution execute() throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        return batchBuilder.init(this)
                .batch("step1", investDealRevokeReader, investDealRevokeProcessor, investDealRevokeWriter)
                .builder("投管交易撤销").run();
    }
}
