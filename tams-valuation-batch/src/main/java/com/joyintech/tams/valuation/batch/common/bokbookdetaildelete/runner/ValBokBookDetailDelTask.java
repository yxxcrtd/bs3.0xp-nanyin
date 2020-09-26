/*
 * 文件名：ValBokBookDetailTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.runner;

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
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.processor.ValBokBookDetailDelProcessor;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.reader.ValBokBookDetailDelReader;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.writer.ValBokBookDetailDelWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.utils.ValDateUtil;

/**
 * 账务明细删除批处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月8日
 * @see ValBokBookDetailDelTask
 * @since 1.0
 */
@Service("valBokBookDetailDelTask")
@Scope("prototype")
public class ValBokBookDetailDelTask extends BatchBaseService {

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
     * step1Reader
     */
    @Autowired
    private ValBokBookDetailDelReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private ValBokBookDetailDelProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private ValBokBookDetailDelWriter step1Writer;
    
    @Autowired
    private BookCommonLogic bookCommonLogic;

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
        Map<String, Object> params = new HashMap<>();
        params.put("BATCHINFO", batchExecuteInfo);// 待处理列表list
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE, ValDateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE, ValDateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        params.put(BookConstant.HAPPEN_DATE, ValDateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", this.getBatchExecuteInfo().getTaskId());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        step1Reader.setParams(params);
        step1Writer.setParams(params);
        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader,
            (BaseProcessor)step1Processor, (BaseWriter)step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }
}
