/*
 * 文件名：ExchangeAutoDurDealGenerateTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.runner;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.processor.ExchangeAutoDurDealGenerateProcessor;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.reader.ExchangeAutoDurDealGenerateReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.writer.ExchangeAutoDurDealGenerateWriter;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;

/**
 * 外汇远期掉期到期交易自动生成
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateTask
 * @since 1.0
 */
@Service("exchangeAutoDurDealGenerateTask")
@Scope("prototype")
public class ExchangeAutoDurDealGenerateTask extends BatchBaseService {

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
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;

    /**
     * ExchangeAutoDurDealGenerateReader
     */
    @Autowired
    private ExchangeAutoDurDealGenerateReader step2Reader;

    /**
     * ExchangeAutoDurDealGenerateProcessor
     */
    @Autowired
    private ExchangeAutoDurDealGenerateProcessor step2Processor;

    /**
     * ExchangeAutoDurDealGenerateWriter
     */
    @Autowired
    private ExchangeAutoDurDealGenerateWriter step2Writer;

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

        params.put(BatchConstant.BATCH_INFO, batchExecuteInfo);
        params.put(BatchConstant.PLATE_DATE, platformDateService.getSysDate());

        // 各步骤设置参数
        step2Reader.setParams(params);
        step2Processor.setParams(params);
        step2Writer.setParams(params);

        return batchBuilder.init(this).batch("step2",
                (BaseReader)step2Reader, (BaseProcessor)step2Processor,
                (BaseWriter)step2Writer).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
