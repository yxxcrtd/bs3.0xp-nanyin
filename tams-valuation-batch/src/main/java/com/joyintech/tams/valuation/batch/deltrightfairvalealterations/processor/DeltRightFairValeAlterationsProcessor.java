/*
 * 文件名：DeltRightFairValeAlterationsProcessor.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.1.4
 * 修改人：wujie
 * 修改时间：2020年10月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.1.4
 */

package com.joyintech.tams.valuation.batch.deltrightfairvalealterations.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.deltrightfairvalealterations.service.DeltRightFairValeAlterationsProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 债权类资产价值处理批处理processor
 * 
 * @author wujie
 * @version 1.0 2020年10月15日
 * @see DeltRightFairValeAlterationsProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DeltRightFairValeAlterationsProcessor extends
        BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private DeltRightFairValeAlterationsProcessorService processerService;

    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity items) throws Exception {

        DrawBaseInfoSelectComResult drawBaseInfoSelectComResult = (DrawBaseInfoSelectComResult) items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        
        // 1.债权类资产公允价值批处理的业务处理
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
