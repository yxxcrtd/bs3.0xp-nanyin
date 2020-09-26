/*
 * 文件名：TaAcctMidFeeAccountsProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王耕耘
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expreturnfeeaccounts.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.PrdCashEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokStatFeePosition;
import com.joyintech.tams.valuation.batch.expreturnfeeaccounts.service.ExpReturnFeeAccountsService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 预期收益型TA中收计提入账批处理-- 处理Processor
 * 
 * @author 王耕耘
 * @version 1.0 2018年10月8日
 * @see TaAcctMidFeeAccountsProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExpReturnFeeAccountsProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResultWithBokStatFeePosition> {

    /**
     * taFloadFeeAccountsService
     */
    @Autowired
    private ExpReturnFeeAccountsService expReturnFeeAccountsService;

    /**
     * 返回记账凭证生成结果
     */

    @Override
    public BookGenerateResultWithBokStatFeePosition process(NeedOperateEntity items)
        throws Exception {
        
        // 获取reader方法的返回值
        PrdCashEntity acctEntity = (PrdCashEntity)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResultWithBokStatFeePosition result = expReturnFeeAccountsService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokStatFeePosition();
        }
        
        result.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }

}
