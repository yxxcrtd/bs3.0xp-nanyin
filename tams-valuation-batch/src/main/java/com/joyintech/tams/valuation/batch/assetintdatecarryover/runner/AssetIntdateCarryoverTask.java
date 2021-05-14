/*
 * 文件名：AssetIntdateCarryoverTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chengzhong
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.runner;

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
import com.joyintech.tams.valuation.batch.assetintdatecarryover.processor.AssetIntdateCarryoverDelProcessor;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.processor.AssetIntdateCarryoverProcessor;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.reader.AssetIntdateCarryoverDelReader;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.reader.AssetIntdateCarryoverReader;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.writer.AssetIntdateCarryoverDelWriter;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.writer.AssetIntdateCarryoverWriter;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产理论结息日结转
 *
 * @author chengzhong
 * @version 1.0 2018年6月4日
 * @see AssetIntdateCarryoverTask
 * @since 1.0
 */
@Service("assetIntdateCarryoverTask")
@Scope("prototype")
public class AssetIntdateCarryoverTask extends BatchBaseService {

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
    private AssetIntdateCarryoverDelReader step1Reader;

    /**
     * step1Processor
     */
    @Autowired
    private AssetIntdateCarryoverDelProcessor step1Processor;

    /**
     * step1Writer
     */
    @Autowired
    private AssetIntdateCarryoverDelWriter step1Writer;
    /**
     * step2Reader
     */
    @Autowired
    private AssetIntdateCarryoverReader step2Reader;

    /**
     * step2Processor
     */
    @Autowired
    private AssetIntdateCarryoverProcessor step2Processor;

    /**
     * step2Writer
     */
    @Autowired
    private AssetIntdateCarryoverWriter step2Writer;
    
    @Autowired
    private BookCommonLogic bookCommonLogic;

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
        /** modify by dongjun on 20190328 通道子账套对应修改  start**/
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        params.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        params.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        params.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", this.getBatchExecuteInfo().getTaskId());
        params.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        params.put("branch", BookConstant.DEFALUT_BRANCH);
        /** modify by dongjun on 20190328 通道子账套对应修改  end**/
        
        // 各步骤设置参数
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
