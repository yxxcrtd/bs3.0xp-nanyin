/*
 * 文件名：AssetIntdateCarryoverProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chenghzong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.cashflowdiscount.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.CashFlowDiscountReaderResult;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.CashFlowDiscountResult;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.service.CashFlowDiscountProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 现金流贴现批处理(处理)
 * @author zznn
 * @date 20210706
 */
@Service
@Scope("prototype")
public class CashFlowDiscountProcessor extends BaseProcessor<NeedOperateEntity, CashFlowDiscountResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private CashFlowDiscountProcessorService cashFlowDiscountProcessorService;

    @Override
    public CashFlowDiscountResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        CashFlowDiscountReaderResult entity = (CashFlowDiscountReaderResult)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put("READER_RESULT", entity);


        // 调用service方法进行逻辑逻辑
        CashFlowDiscountResult result = cashFlowDiscountProcessorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new CashFlowDiscountResult();
        }

        return result;
    }
}
