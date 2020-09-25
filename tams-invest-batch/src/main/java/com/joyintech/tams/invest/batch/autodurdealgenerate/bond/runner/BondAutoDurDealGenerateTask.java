/*
 * 文件名：BondAutoDurDealGenerateTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.processor.BondAutoDurDealGenerateDelProcessor;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.processor.BondAutoDurDealGenerateProcessor;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.reader.BondAutoDurDealGenerateDelReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.reader.BondAutoDurDealGenerateReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.writer.BondAutoDurDealGenerateDelWriter;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.writer.BondAutoDurDealGenerateWriter;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 债券存续期 自动生成债券还本、收息、到期交易
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see BondAutoDurDealGenerateTask
 * @since 1.0
 */
@Service("bondAutoDurDealGenerateTask")
@Scope("prototype")
public class BondAutoDurDealGenerateTask extends BatchBaseService {

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
     * BondAutoDurDealGenerateDelReader
     */
    @Autowired
    private BondAutoDurDealGenerateDelReader step1Reader;

    /**
     * BondAutoDurDealGenerateDelProcessor
     */
    @Autowired
    private BondAutoDurDealGenerateDelProcessor step1Processor;

    /**
     * BondAutoDurDealGenerateWriter
     */
    @Autowired
    private BondAutoDurDealGenerateDelWriter step1Writer;

    /**
     * BondAutoDurDealGenerateReader
     */
    @Autowired
    private BondAutoDurDealGenerateReader step2Reader;

    /**
     * BondAutoDurDealGenerateProcessor
     */
    @Autowired
    private BondAutoDurDealGenerateProcessor step2Processor;

    /**
     * BondAutoDurDealGenerateWriter
     */
    @Autowired
    private BondAutoDurDealGenerateWriter step2Writer;

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
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        step2Reader.setParams(params);
        step2Processor.setParams(params);
        step2Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).batch("step2", (BaseReader)step2Reader,
            		(BaseProcessor)step2Processor, (BaseWriter)step2Writer).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
