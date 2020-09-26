/*
 * 文件名：BookAccountGenerateTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookaccountgenerate.runner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateDelEntity;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateDelResult;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateReadResult;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateResult;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.processor.BookAccountGenerateDelProcessor;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.processor.BookAccountGenerateProcessor;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.reader.BookAccountGenerateDelReader;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.reader.BookAccountGenerateReader;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.writer.BookAccountGenerateDelWriter;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.writer.BookAccountGenerateWriter;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 凭证生成
 *
 * @author 杨智
 * @version 1.0 2018年5月23日
 * @see BookAccountGenerateTask
 * @since 1.0
 */
@Service("bookAccountGenerateTask")
@Scope("prototype")
public class BookAccountGenerateTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    /**
     * step0Reader
     */
    @Autowired
    private BookAccountGenerateDelReader step0Reader;

    /**
     * step0Processor
     */
    @Autowired
    private BookAccountGenerateDelProcessor step0Processor;

    /**
     * step0Writer
     */
    @Autowired
    private BookAccountGenerateDelWriter step0Writer;

    /**
     * step1Reader
     */
    @Autowired
    private BookAccountGenerateReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private BookAccountGenerateProcessor step1Processor;

    /**
     * 账务共通方法
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * step1Writer
     */
    @Autowired
    private BookAccountGenerateWriter step1Writer;

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
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(
            this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE,
            DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE,
            DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        params.put(BookConstant.HAPPEN_DATE,
            DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", this.getBatchExecuteInfo().getTaskId());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        // params.put("booksetIdList", new ArrayList<String>(bookSetIds));
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        step0Reader.setParams(params);
        step0Processor.setParams(params);
        step0Writer.setParams(params);

        return batchBuilder.init(this).batch("step0", (BaseReader)step0Reader,
            (BaseProcessor)step0Processor, (BaseWriter)step0Writer).batch("step1",
                (BaseReader)step1Reader, (BaseProcessor)step1Processor,
                (BaseWriter)step1Writer).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
