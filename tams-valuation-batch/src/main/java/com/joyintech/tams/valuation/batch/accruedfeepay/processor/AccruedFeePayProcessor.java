/*
 * 文件名：AccruedFeePayProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.accruedfeepay.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.accruedfeepay.service.AccruedFeePayProcessorService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.tadealaccounts.processor.TaDealAccountsProcessor;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * TA计提式费用支付批处理 - processor
 * 
 * @author niuqianyu
 * @version 1.0 2018年6月11日
 * @see TaDealAccountsProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AccruedFeePayProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private AccruedFeePayProcessorService accruedFeePayProcessorService;


    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)items;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        

        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = accruedFeePayProcessorService.execute(thisParams);
        
        //当为null时候，给 予初始化
        if(result == null){
            result = new BookGenerateResult();
        }
        
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
