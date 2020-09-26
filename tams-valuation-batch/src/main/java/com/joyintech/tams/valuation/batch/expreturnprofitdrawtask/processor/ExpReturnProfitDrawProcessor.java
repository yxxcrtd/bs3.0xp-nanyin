/*
 * 文件名：YelidPrdProfitDrawProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expreturnprofitdrawtask.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.StayDrawSubprdEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.expreturnprofitdrawtask.service.ExpReturnProfitDrawProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 预期收益型子产品TA客户收益计提-账务明细处理Processor
 * 
 * @author dongjun
 * @version 1.0 2018年9月29日
 * @see ExpReturnProfitDrawProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExpReturnProfitDrawProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    private ExpReturnProfitDrawProcessorService expReturnProfitDrawProcessorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookGenerateResult process(NeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值
        StayDrawSubprdEntity stayDrawSubprdEntity = (StayDrawSubprdEntity)item;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, stayDrawSubprdEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = expReturnProfitDrawProcessorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new BookGenerateResult();
        }

        result.setProdId(stayDrawSubprdEntity.getBooksetId() + GlobalConstant.SPLIT + stayDrawSubprdEntity.getBooksetId());

        return result;
    }

}
