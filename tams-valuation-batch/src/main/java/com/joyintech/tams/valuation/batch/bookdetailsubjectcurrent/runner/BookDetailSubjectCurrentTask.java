/*
 * 文件名：BookDetailSubjectAllTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.runner;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.processor.BookDetailSubjectCopyProcessor;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.processor.BookDetailSubjectCurrentProcessor;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.reader.BookDetailSubjectCopyReader;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.reader.BookDetailSubjectCurrentReader;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.writer.BookDetailSubjectCopyWriter;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.writer.BookDetailSubjectCurrentWriter;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 更新账务明细（历史表）科目余额批处理
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailSubjectCurrentTask
 * @since 1.0
 */
@Service("bookDetailSubjectCurrentTask")
@Scope("prototype")
public class BookDetailSubjectCurrentTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, 
                                                                    BaseWriter<NeedToWriteEntity>> batchBuilder;

    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * step1Reader
     */
    @Autowired
    private BookDetailSubjectCopyReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private BookDetailSubjectCopyProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private BookDetailSubjectCopyWriter step1Writer;

    /**
     * step2Reader
     */
    @Autowired
    private BookDetailSubjectCurrentReader step2Reader;

    /**
     * step2Processor
     */
    @Autowired
    private BookDetailSubjectCurrentProcessor step2Processor;

    /**
     * step2Writer
     */
    @Autowired
    private BookDetailSubjectCurrentWriter step2Writer;
    
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

        // 待处理列表list
        params.put("BATCHINFO", batchExecuteInfo);
        params.put("BATCHDEL", this.getBatchExecuteDetail());
        params.put("BATCHTASK", this.getBatchTask());
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        params.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", this.getBatchExecuteInfo().getTaskId());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
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
