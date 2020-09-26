/*
 * 文件名：prdFloatFeeExpensesUpProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.prdFloatFeeExpensesUp.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.prdFloatFeeExpensesUp.service.PrdFloatFeeExpensesUpProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 浮动管理费挂账批处理Processor
 * 
 * @author zhangJin
 * @version 1.0 2019年9月26日
 * @see PrdFloatFeeExpensesUpProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdFloatFeeExpensesUpProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private PrdFloatFeeExpensesUpProcessorService prdFloatFeeExpensesUpProcessorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());

        // 调用service方法进行逻辑处理
        BookGenerateResult result = prdFloatFeeExpensesUpProcessorService.execute(thisParams,
            acctEntity);

        // 当为null时候，给 予初始化
        if (result == null) {
            result = new BookGenerateResult();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
