/*
 * 文件名：ProductReportTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.productreport.runner;

import com.joyintech.batch.batchbase.baseclass.*;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.batch.psbc.productreport.processor.ProductReportProcessor;
import com.joyintech.tams.batch.psbc.productreport.reader.ProductReportReader;
import com.joyintech.tams.batch.psbc.productreport.writer.ProductReportWriter;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 理财报表生成批处理
 *
 * @author jiangpeng
 * @version 1.0 2020/12/03
 * @see ProductReportTask
 * @since 1.0
 */
@Service("productReportTask")
@Scope("prototype")
public class ProductReportTask extends BatchBaseService {

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
    private ProductReportReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private ProductReportProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private ProductReportWriter step1Writer;

    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;

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
        // 自动批处理，取平台日期为跑批日，手动批处理，取画面选择日期为跑批日
        Date batchDate = batchExecuteInfo.getExecuteDate();
        if (StringUtil.equals(BatchDictCodeList.批处理启动模式_自动.getValue(),
                batchExecuteInfo.getStartMode())) {
            batchDate = platformDateService.getSysDate();
        }
        Map<String, Object> params = new HashMap<>();
        params.put(BookConstant.BOOK_DATE, batchDate);
        params.put("productIdList", this.getProductIdList());

        // 各步骤设置参数
        step1Reader.setParams(params);
        step1Processor.setParams(params);
        step1Writer.setParams(params);
        return batchBuilder.init(this).batch("step1", (BaseReader) step1Reader,
                (BaseProcessor) step1Processor, (BaseWriter) step1Writer).builder(
                this.getBatchTask().getBeanName() + this.getBatchTask().getTaskId()).run();
    }

}
