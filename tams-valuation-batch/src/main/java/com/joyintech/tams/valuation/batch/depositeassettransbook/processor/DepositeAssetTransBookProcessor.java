/*
 * 文件名：DepositeAssetTransBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.depositeassettransbook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.depositeassettransbook.service.DepositeAssetTransBookService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 存款类资产交易账务处理 - processor
 * 
 * @author weipeng
 * @version 1.0 2018年5月17日
 * @see DepositeAssetTransBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetTransBookProcessor extends
        BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private DepositeAssetTransBookService depositeAssetTransBookService;

    @Override
    public BookGenerateResult process(NeedOperateEntity items) throws Exception {
        // 获取reader方法的返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult) items;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = depositeAssetTransBookService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResult();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
