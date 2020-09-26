/*
 * 文件名：SecLendFinFloatBookDetailTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.seclendfinfloatbookdetail.runner;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
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
import com.joyintech.tams.valuation.batch.seclendfinfloatbookdetail.processor.SecLendFinFloatBookDetailProcessor;

/**
 * 债券借贷标的债券公允价值变动处理 task
 * 
 * @author zhangwei
 * @version 1.0 2018年10月25日
 * @see SecLendFinFloatBookDetailTask
 * @since 1.0
 */
@Service("secLendFinFloatBookDetailTask")
@Scope("prototype")
public class SecLendFinFloatBookDetailTask extends BatchBaseService {

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
     * step1Reader - 计提批处理基本信息读入共通reader
     */
    @Autowired
    private DrawBaseInfoSelectComReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private SecLendFinFloatBookDetailProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private BookGenerateResultForCurrentCommonWriterWithBokSummary step1Writer;

    @SuppressWarnings({"unchecked", "rawtypes"})
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
        Map<String, Object> params = Maps.newHashMap();

        // 待处理列表list
        params.put("BATCHINFO", batchExecuteInfo);

        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader, (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
            this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
