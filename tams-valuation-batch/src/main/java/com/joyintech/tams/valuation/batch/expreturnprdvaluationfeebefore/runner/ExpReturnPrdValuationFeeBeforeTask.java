/*
 * 文件名：ProBenefitPrdValuationFeeBeforeTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.runner;

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
import com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.processor.ExpReturnPrdValuationFeeBeforeProcessor;
import com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.reader.ExpReturnPrdValuationFeeBeforeReader;
import com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.writer.ExpReturnPrdValuationFeeBeforeWriter;

/**
 * (预期收益型产品估值计算-费前) - 批处理
 * 
 * @author niuqianyu
 * @version 1.0 2018年9月29日
 * @see ExpReturnPrdValuationFeeBeforeTask
 * @since 1.0
 */
@Service("expReturnPrdValuationFeeBeforeTask")
@Scope("prototype")
public class ExpReturnPrdValuationFeeBeforeTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    /**
     * step1Reader
     */
    @Autowired
    private ExpReturnPrdValuationFeeBeforeReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private ExpReturnPrdValuationFeeBeforeProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private ExpReturnPrdValuationFeeBeforeWriter step1Writer;

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

        // 待处理列表list
        params.put("BATCHINFO", batchExecuteInfo);

        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
