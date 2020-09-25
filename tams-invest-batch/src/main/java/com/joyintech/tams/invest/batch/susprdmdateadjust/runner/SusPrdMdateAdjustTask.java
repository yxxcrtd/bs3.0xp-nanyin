/*
 * 文件名：ResetBaseRuleInfoTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdmdateadjust.runner;

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
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.susprdmdateadjust.processor.SusPrdMdateAdjustProcessor;
import com.joyintech.tams.invest.batch.susprdmdateadjust.reader.SusPrdMdateAdjustReader;
import com.joyintech.tams.invest.batch.susprdmdateadjust.writer.SusPrdMdateAdjustWriter;

/**
 *  批处理-续投时期次结束日调整批处理
 */
@Service("susPrdMdateAdjustTask")
@Scope("prototype")
public class SusPrdMdateAdjustTask extends BatchBaseService{

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

    @Autowired
    private SusPrdMdateAdjustReader stepReader;

    @Autowired
    private SusPrdMdateAdjustProcessor stepProcessor;

    @Autowired
    private SusPrdMdateAdjustWriter stepWriter;

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
        stepReader.setParams(params);
        stepProcessor.setParams(params);
        stepWriter.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)stepReader,
            (BaseProcessor)stepProcessor, (BaseWriter)stepWriter).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
    
}
