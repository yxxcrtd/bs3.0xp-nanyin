/*
 * 文件名：YelidPrdProfitDrawTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expreturnprofitdrawtask.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.bookgenerateresult.BookGenerateResultForCurrentCommonWriter;
import com.joyintech.tams.valuation.batch.expreturnprofitdrawtask.processor.ExpReturnProfitDrawProcessor;
import com.joyintech.tams.valuation.batch.expreturnprofitdrawtask.reader.ExpReturnProfitDrawReader;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 预期收益型子产品TA客户收益计提-账务明细处理
 * 
 * @author dongjun
 * @version 1.0 2018年9月29日
 * @see ExpReturnProfitDrawTask
 * @since 1.0
 */
@Service("expReturnProfitDrawTask")
@Scope("prototype")
public class ExpReturnProfitDrawTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * batchBuilder
     */
    @Autowired
    private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

    /**
     * step1Reader
     */
    @Autowired
    private ExpReturnProfitDrawReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private ExpReturnProfitDrawProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private BookGenerateResultForCurrentCommonWriter step1Writer;
    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public JobExecution execute()
        throws BatchException {
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        List<String> cashTypeList = new ArrayList<>();
        cashTypeList.add(DictCodeListExt1.现金流二级类型_利息.getValue());
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
        params.put("cdate", this.getBatchExecuteInfo().getExecuteDate());
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", this.getBatchExecuteInfo().getTaskId());
        params.put("batchInfo", this.getBatchExecuteInfo());
        params.put("cashTypeList", cashTypeList);
        params.put("profitType", DictCodeList.收益类型_收益型.getValue());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        
        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
