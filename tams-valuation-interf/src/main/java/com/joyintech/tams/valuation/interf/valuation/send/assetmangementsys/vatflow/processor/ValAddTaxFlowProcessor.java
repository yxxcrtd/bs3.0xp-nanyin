/*
 * 文件名：ValAddTaxFlowProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年4月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.service.ValAddTaxFlowService;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 增值税流水回传接口
 * @author wangqidong
 * @version 1.0 2019年4月23日
 * @see ValAddTaxFlowProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValAddTaxFlowProcessor extends InterfBaseProcessor<SendReaderEntity, ValAddTaxFlowprocessorResult>{

    @Autowired
    private ValAddTaxFlowService valAddTaxFlowService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ValAddTaxFlowprocessorResult process(SendReaderEntity item)
        throws Exception {
        ValAddTaxFlowprocessorResult valAddTaxFlowprocessorResult = valAddTaxFlowService.execute(item);
        return valAddTaxFlowprocessorResult;
    }

}
