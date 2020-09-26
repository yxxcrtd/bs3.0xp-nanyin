/*
 * 文件名：ProfitAndLossAccountingProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.hqinfo.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.informationsys.hqinfo.dto.HqDetailInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.informationsys.hqinfo.service.HqDetailInfoInfoRecService;
/**
 * 净值型产品产品信息接口 Processor
 * @author wangwei1
 * @version 1.0 2018年6月28日
 * @see HqDetailInfoInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqDetailInfoInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, HqDetailInfoProcessorResult> {
    
    @Autowired
    private HqDetailInfoInfoRecService service;  
	
    @Override
    public HqDetailInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        
        //调用信息处理公用service
        HqDetailInfoProcessorResult  result = service.execute(acctEntity); 
        
        return result;
    }

}
