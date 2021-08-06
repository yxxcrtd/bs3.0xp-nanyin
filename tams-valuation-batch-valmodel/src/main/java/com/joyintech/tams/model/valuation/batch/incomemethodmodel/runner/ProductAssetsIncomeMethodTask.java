/*
 * 文件名：ResetBaseRuleInfoTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.model.valuation.batch.incomemethodmodel.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.listener.ProductAssetsIncomeMethodListener;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.processor.ProductAssetsIncomeMethodProcessor;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.reader.ProductAssetsIncomeMethodReader;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.writer.ProductAssetsIncomeMethodWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:收益法估值批处理Task
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service("productAssetsIncomeMethodTask")
@Scope("prototype")
public class ProductAssetsIncomeMethodTask extends BatchBaseService{

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
     * 收益法估值批处理Reader
     */
    @Autowired
    private ProductAssetsIncomeMethodReader step1Reader;

    /**
     * 收益法估值批处理Processor
     */
    @Autowired
    private ProductAssetsIncomeMethodProcessor step1Processor;

    /**
     * 收益法估值批处理Writer
     */
    @Autowired
    private ProductAssetsIncomeMethodWriter step1Writer;

    @Autowired
    private ProductAssetsIncomeMethodListener productAssetsIncomeMethodListener;


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
        params.put("BATCH_INFO", batchExecuteInfo);

//        把执行日期当作平台日来替换处理
        params.put("PLATE_DATE", batchExecuteInfo.getExecuteDate());
        params.put("TASK_ID",batchExecuteInfo.getTaskId());
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        JobExecution jobExecution = batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                    this.getBatchTask().getBeanName()  + GlobalConstant.UNDER_LINE + this.getBatchTask().getTaskId()
        , productAssetsIncomeMethodListener).run();



        return jobExecution;
    }
    
}
