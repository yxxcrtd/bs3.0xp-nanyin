/*
 * 文件名：BankCalProvisionTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankcalbook.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.psbc.bankcalbook.processor.BankCalProvisionProcessor;
import com.joyintech.tams.valuation.batch.psbc.bankcalbook.reader.BankCalProvisionReader;
import com.joyintech.tams.valuation.batch.psbc.bankcalbook.writer.BankCalProvisionWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行账计提账务处理
 * 
 * @author wangyang1
 * @version 1.0 2019年8月9日
 * @see BankCalProvisionTask
 * @since 1.0
 */
@Service("bankCalProvisionTask")
@Scope("prototype")
public class BankCalProvisionTask extends BatchBaseService {

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
    private BankCalProvisionReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private BankCalProvisionProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private BankCalProvisionWriter step1Writer;
    
    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

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

        logger.info("批处理" + this.getBatchTask().getTaskName() + "开始。");
        // 各步骤设置参数
        Map<String, Object> params = new HashMap<>();
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

        return batchBuilder.init(this).batch("step1", (BaseReader)step1Reader, (BaseProcessor)step1Processor,
            (BaseWriter)step1Writer).builder(this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
