/*
 * 文件名：DeltRightDealBookTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.deltrightdealbook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.deltrightdealbook.service.DeltRightDealBookProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 债权类交易账务批处理 Processor
 * @author weipeng
 * @version 1.0 2018年6月6日
 * @see DeltRightDealBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DeltRightDealBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {
    
    /**
     * processor方法的 Service
     */
    @Autowired
    private DeltRightDealBookProcessorService deltRightDealBookProcessorService;

    @Override
    public BookGenerateResult process(NeedOperateEntity items) throws Exception {
        // 获取reader方法的返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult) items;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = deltRightDealBookProcessorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResult();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
