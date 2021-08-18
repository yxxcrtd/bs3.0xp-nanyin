/*
 * 文件名：ValuationBatchApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.model.impairment.batch.pdcalculate.runner;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.pdcalculate.listener.PdCalculateListener;
import com.joyintech.tams.model.impairment.batch.pdcalculate.processor.PdCalculateDelProcessor;
import com.joyintech.tams.model.impairment.batch.pdcalculate.processor.PdCalculateProcessor;
import com.joyintech.tams.model.impairment.batch.pdcalculate.reader.PdCalculateDelReader;
import com.joyintech.tams.model.impairment.batch.pdcalculate.reader.PdCalculateReader;
import com.joyintech.tams.model.impairment.batch.pdcalculate.writer.PdCalculateDelWriter;
import com.joyintech.tams.model.impairment.batch.pdcalculate.writer.PdCalculateWriter;
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

/**
 * @Description:减值-PD计算批处理
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service("pdCalculateTask")
@Scope("prototype")
public class PdCalculateTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private PdCalculateListener pdCalculateListener;

    /**
     * 减值-PD计算删除批处理 step1Reader
     */
    @Autowired
    private PdCalculateDelReader step1Reader;

    /**
     * 减值-PD计算删除批处理 step1Processor
     */
    @Autowired
    private PdCalculateDelProcessor step1Processor;

    /**
     * 减值-PD计算删除批处理 step1Writer
     */
    @Autowired
    private PdCalculateDelWriter step1Writer;
    /**
     * 减值-PD计算批处理 step2Reader
     */
    @Autowired
    private PdCalculateReader step2Reader;

    /**
     * 减值-PD计算批处理 step2Processor
     */
    @Autowired
    private PdCalculateProcessor step2Processor;

    /**
     * 减值-PD计算批处理 step2Writer
     */
    @Autowired
    private PdCalculateWriter step2Writer;



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
        params.put("PLATE_DATE", batchExecuteInfo.getExecuteDate());
        // 待处理列表list
        params.put("BATCH_INFO", batchExecuteInfo);
        params.put("TASK_ID", this.getBatchExecuteInfo().getTaskId());

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
                this.getBatchTask().getBeanName() + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId(),pdCalculateListener).run();

    }

}
