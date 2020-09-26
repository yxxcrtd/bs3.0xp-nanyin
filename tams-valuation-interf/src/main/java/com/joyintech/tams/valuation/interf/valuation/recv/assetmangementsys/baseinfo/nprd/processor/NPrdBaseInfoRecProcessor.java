/*
 * 文件名：ProfitAndLossAccountingProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.dto.NPrdBaseInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.service.NPrdBaseInfoRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.service.NPrdBaseInfoRecjxService;

/**
 * 
 * 净值型产品产品信息接口 Processor
 * @author weipeng
 * @version 1.0 2018年6月25日
 * @see NPrdBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, NPrdBaseInfoProcessorResult> {

    @Autowired
    private NPrdBaseInfoRecService nPrdBaseInfoRecService;   
    
    @Autowired
    private NPrdBaseInfoRecjxService nPrdBaseInfoRecjxService;  
    
    
    @Override
    public NPrdBaseInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        //调用净值型基础信息处理公用service
        NPrdBaseInfoProcessorResult  result = nPrdBaseInfoRecService.execute(acctEntity); 
        //几许传数
        nPrdBaseInfoRecjxService.prodInfoJx(result);
        return result;
    }

}
