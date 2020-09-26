/*
 * 文件名：OtcOptionFairValueTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.otcoptionfairvalue.runner;

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
import com.joyintech.tams.valuation.batch.common.bookgenerateresult.BookGenerateResultForCurrentCommonWriterWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.reader.DrawBaseInfoSelectComReader;
import com.joyintech.tams.valuation.batch.otcoptionfairvalue.processor.OtcOptionFairValueProcessor;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2020年1月2日
 * @see OtcOptionFairValueTask
 * @since 1.0
 */
@Service("otcOptionFairValueTask")
@Scope("prototype")
public class OtcOptionFairValueTask extends BatchBaseService{
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * BatchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, 
                        NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;
    
    /**
     * step1Reader
     */
    @Autowired
    private DrawBaseInfoSelectComReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private OtcOptionFairValueProcessor step1Processor;
    
    /**
     * step1Writer
     */
    @Autowired
    private BookGenerateResultForCurrentCommonWriterWithBokSummary step1Writer;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public JobExecution execute()
        throws BatchException {
        // 获取批处理信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        
        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
