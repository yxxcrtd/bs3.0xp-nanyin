/*
 * 文件名：SecFairValeAlterationsProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.secfairvalealterations.processor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.secfairvalealterations.service.SecFairValeAlterationsProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 债券公园价值处理批处理processor
 * 
 * @author wudebao
 * @version 1.0 2018年5月3日
 * @see SecFairValeAlterationsProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecFairValeAlterationsProcessor extends
        BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * processor方法的 Service
     */
    @Autowired
    private SecFairValeAlterationsProcessorService processerService;

    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity items) throws Exception {

//        Map<String, Object> param1 = new HashMap<>();
        DrawBaseInfoSelectComResult drawBaseInfoSelectComResult = (DrawBaseInfoSelectComResult) items;
//        logger.info("开始处理对象: 账套代码  = " + drawBaseInfoSelectComResult.getBooksetId());
//        logger.info("开始处理对象: 业务明细代码  = " + drawBaseInfoSelectComResult.getBokDetailId());
//        logger.info("开始处理对象: 投资目的 = " + drawBaseInfoSelectComResult.getInvAim());

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        
        // 1.拆借交易登账处理
        BookGenerateResultWithBokSummary serviceDealResult = processerService.execute(drawBaseInfoSelectComResult, thisParams);

        //当为null时候，给予初始化
        if(serviceDealResult == null){
            serviceDealResult = new BookGenerateResultWithBokSummary();
        }
        
        serviceDealResult.setProdId(
            drawBaseInfoSelectComResult.getBooksetId() + GlobalConstant.SPLIT + drawBaseInfoSelectComResult.getBokDetailId());

        return serviceDealResult;
    }

}
