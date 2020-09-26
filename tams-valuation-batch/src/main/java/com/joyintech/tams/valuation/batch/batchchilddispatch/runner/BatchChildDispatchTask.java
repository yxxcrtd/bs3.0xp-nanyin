/*
 * 文件名：BatchChildDispatchTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchchilddispatch.runner;

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
import com.joyintech.tams.valuation.batch.batchchilddispatch.proseccor.BatchChildDispatchProcessor;
import com.joyintech.tams.valuation.batch.batchchilddispatch.reader.BatchChildDispatchReader;
import com.joyintech.tams.valuation.batch.batchchilddispatch.writer.BatchChildDispatchWriter;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 子调度批处理
 * 生成子任务调度信息
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchTask
 * @since 1.0
 */
@Service("batchChildDispatchTask")
@Scope("prototype")
public class BatchChildDispatchTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private BatchChildDispatchReader batchChildDispatchReader;
    
    @Autowired
    private BatchChildDispatchProcessor batchChildDispatchProcessor;
    
    @Autowired
    private BatchChildDispatchWriter batchChildDispatchWriter;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public JobExecution execute()
        throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.warn("无启动参数，批处理退出。");
            return null;
        }
        Map<String,Object> params = new HashMap<>();
        params.put(GlobalConstant.BOOKSETS, this.getProductIdList());
        batchChildDispatchReader.setParams(params);
        return batchBuilder.init(this).batch("step1", (BaseReader)batchChildDispatchReader,
            (BaseProcessor)batchChildDispatchProcessor,
            (BaseWriter)batchChildDispatchWriter).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
