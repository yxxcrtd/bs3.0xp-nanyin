/*
 * 文件名：ValSchemeTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valscheme.runner;

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
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.valfilegendel.processor.ValFileGenDelProcessor;
import com.joyintech.tams.valuation.batch.common.valfilegendel.reader.ValFileGenDelReader;
import com.joyintech.tams.valuation.batch.common.valfilegendel.writer.ValFileGenDelWriter;
import com.joyintech.tams.valuation.batch.valscheme.processor.ValSchemeDelProcessor;
import com.joyintech.tams.valuation.batch.valscheme.processor.ValSchemeProcessor;
import com.joyintech.tams.valuation.batch.valscheme.reader.ValSchemeReader;
import com.joyintech.tams.valuation.batch.valscheme.writer.ValSchemeDelWriter;
import com.joyintech.tams.valuation.batch.valscheme.writer.ValSchemeWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 估值表生成
 * 
 * @author yangzhi
 * @version 1.0 2018年5月31日
 * @see ValSchemeTask
 * @since 1.0
 */
@Service("valSchemeTask")
@Scope("prototype")
public class ValSchemeTask extends BatchBaseService {
    
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

    /**
     * step0Reader
     */
    @Autowired
    private ValFileGenDelReader step0Reader;

    /**
     * step0Processor
     */
    @Autowired
    private ValFileGenDelProcessor step0Processor;

    /**
     * step0Writer
     */
    @Autowired
    private ValFileGenDelWriter step0Writer;
    
    /**
     * step1Reader
     */
    @Autowired
    private ValSchemeReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private ValSchemeDelProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private ValSchemeDelWriter step1Writer;
    
    /**
     * step1Reader
     */
    @Autowired
    private ValSchemeReader step2Reader;
    
    /**
     * step1Processor
     */
    @Autowired
    private ValSchemeProcessor step2Processor;
    
    @Autowired
    private BookCommonLogic bookCommonLogic;
    /**
     * step1Writer
     */
    @Autowired
    private ValSchemeWriter step2Writer;

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
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", batchExecuteInfo.getTaskId());// 任务代码放入params中
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        params.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put(DictCode.文件类型.getValue(), DictCodeList.文件类型_估值表.getValue());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        // 各步骤设置参数
        step2Reader.setParams(params);
        step2Processor.setParams(params);
        step2Writer.setParams(params);
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        step0Reader.setParams(params);
        step0Processor.setParams(params);
        step0Writer.setParams(params);
        return batchBuilder.init(this).batch("step0", (BaseReader)step0Reader,
            (BaseProcessor)step0Processor, (BaseWriter)step0Writer).batch("step1",
                (BaseReader)step1Reader, (BaseProcessor)step1Processor,
                (BaseWriter)step1Writer).batch("step2",
                    (BaseReader)step2Reader, (BaseProcessor)step2Processor,
                    (BaseWriter)step2Writer).builder(
                    this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
