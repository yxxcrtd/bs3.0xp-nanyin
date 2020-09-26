/*
 * 文件名：MoneyTaFloatFeeBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年6月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.moneytafloatfeebook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.StayDrawSubprdEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokStatFeePosition;
import com.joyintech.tams.valuation.batch.moneytafloatfeebook.service.MoneyTaFloadFeeBookService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 货币型TA浮动费用计提入账批处理 -- 处理Processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月5日
 * @see MoneyTaFloatFeeBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaFloatFeeBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResultWithBokStatFeePosition> {

    /**
     * taFloadFeeAccountsService
     */
    @Autowired
    private MoneyTaFloadFeeBookService moneyTaFloadFeeBookService;

    /**
     * 返回记账凭证生成结果
     */

    @Override
    public BookGenerateResultWithBokStatFeePosition process(NeedOperateEntity items)
        throws Exception {
        
        // 获取reader方法的返回值
        StayDrawSubprdEntity acctEntity = (StayDrawSubprdEntity)items;//modify by dongjun on 20190115

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResultWithBokStatFeePosition result = moneyTaFloadFeeBookService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokStatFeePosition();
        }
        
        result.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }

}
