/*
 * 文件名：MoneyTaAutoCalTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.moneytaautocal.runner;

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
import com.joyintech.tams.valuation.batch.moneytaautocal.processor.MoneyTaAutoCalDelProcessor;
import com.joyintech.tams.valuation.batch.moneytaautocal.processor.MoneyTaAutoCalProcessor;
import com.joyintech.tams.valuation.batch.moneytaautocal.reader.MoneyTaAutoCalDelReader;
import com.joyintech.tams.valuation.batch.moneytaautocal.reader.MoneyTaAutoCalReader;
import com.joyintech.tams.valuation.batch.moneytaautocal.writer.MoneyTaAutoCalDelWriter;
import com.joyintech.tams.valuation.batch.moneytaautocal.writer.MoneyTaAutoCalWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;

/**
 * 货币型产品自动结转处理
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see MoneyTaAutoCalTask
 * @since 1.0
 */
@Service("moneyTaAutoCalTask")
@Scope("prototype")
public class MoneyTaAutoCalTask extends BatchBaseService {
    
    @Autowired
    private MoneyTaAutoCalDelReader step1Reader;

    @Autowired
    private MoneyTaAutoCalDelProcessor step1Processor;
    
    @Autowired
    private MoneyTaAutoCalDelWriter step1Writer;
    
    @Autowired
    private MoneyTaAutoCalReader step2Reader;

    @Autowired
    private MoneyTaAutoCalProcessor step2Processor;
    
    @Autowired
    private MoneyTaAutoCalWriter step2Writer;
    
    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, 
                                                                    BaseWriter<NeedToWriteEntity>> batchBuilder;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @SuppressWarnings({"rawtypes", "unchecked"})
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
