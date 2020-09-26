/*
 * 文件名：OtcOptionFairValueProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.otcoptionfairvalue.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.otcoptionfairvalue.service.OtcOptionFairValueProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2020年1月2日
 * @see OtcOptionFairValueProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OtcOptionFairValueProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    private OtcOptionFairValueProcessorService otcOptionFairValueProcessorService;
    /**
     * {@inheritDoc}
     */
    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity items)
        throws Exception {
        DrawBaseInfoSelectComResult drawBaseInfoSelectComResult = (DrawBaseInfoSelectComResult) items;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        BookGenerateResultWithBokSummary result = otcOptionFairValueProcessorService.execute(drawBaseInfoSelectComResult,thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokSummary();
        }

        result.setProdId(
            drawBaseInfoSelectComResult.getBooksetId() + GlobalConstant.SPLIT + drawBaseInfoSelectComResult.getBokDetailId());
        return result;
    }

}
