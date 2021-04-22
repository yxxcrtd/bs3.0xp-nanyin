/*
 * 文件名：AssetIntdateCarryoverTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chengzhong
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.assetimpairmentprovision.runner;

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
import com.joyintech.tams.valuation.batch.assetimpairmentprovision.processor.AssetImpairmentProvisionProcessor;
import com.joyintech.tams.valuation.batch.assetimpairmentprovision.reader.AssetImpairmentProvisionTaskReader;
import com.joyintech.tams.valuation.batch.assetimpairmentprovision.writer.AssetImpairmentProvisionWriter;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产减值计提批处理
 *
 * @author chufengjie
 * @version 1.0 2020年10月13日
 * @see AssetImpairmentProvisionTask
 * @since 1.0
 */
@Service("assetImpairmentProvisionTask")
@Scope("prototype")
public class AssetImpairmentProvisionTask extends BatchBaseService {

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
     * step1Reader
     */
    @Autowired
    private AssetImpairmentProvisionTaskReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private AssetImpairmentProvisionProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private AssetImpairmentProvisionWriter step1Writer;
    

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
        params.put(GlobalConstant.BOOKSETS, this.getProductIdList());
        params.put("BATCHTASK", this.getBatchTask());

        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        
        logger.info("批处理[" + this.getBatchTask().getTaskName() + "]开始。");
        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
