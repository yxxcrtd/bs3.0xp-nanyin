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
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
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
public class CashFlowDiscountProcessor extends BaseProcessor<NeedOperateEntity, CashFlowDiscountResult> implements StepExecutionListener {

    /**
     * processor方法的 Service
     */
    @Autowired
    private CashFlowDiscountProcessorService cashFlowDiscountProcessorService;
    private StepExecution stepExecution;

    @Override
    public CashFlowDiscountResult process(NeedOperateEntity items)
        throws Exception {

        // 调用service方法进行逻辑逻辑
        CashFlowDiscountResult result = cashFlowDiscountProcessorService.execute(items,getParams(),stepExecution);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new CashFlowDiscountResult();
        }

        return result;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }

}
