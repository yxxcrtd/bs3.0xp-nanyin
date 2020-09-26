/*
 * 文件名：TaxInfoRecProcess.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.processor;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dto.FeeInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.service.FeeInfoRecService;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendWriterEntity;
/**
 * 产品费用Processor
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月19日
 * @see FeeInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FeeInfoRecProcessor  extends InterfBaseProcessor<SendReaderEntity, SendWriterEntity>{
    /**
     * 产品费用Service
     */
    @Autowired
    private FeeInfoRecService feeInfoRecService;
    
    /**
     * process处理方法
     */
    @Override
    public SendWriterEntity process(SendReaderEntity item) throws Exception {
        // TODO Auto-generated method stub
        // 获取reader方法的返回值(processor并发数据结构)
        ValAddTaxFlowprocessorResult feeInfoProcessorResult  = feeInfoRecService.execute(item);
        return feeInfoProcessorResult;
    }
    
}
