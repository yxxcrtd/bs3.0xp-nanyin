/*
 * 文件名：CPlannedAssetBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cplannedinfo.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cplannedinfo.dto.CPlannedAssetBaseInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cplannedinfo.service.CPlannedAssetBaseInfoRecService;
/**
 * 计划类货币型-基础数据接口Processor
 * @author zhangzhao
 * @version 1.0 2019年1月22日
 * @see CPlannedAssetBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPlannedAssetBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, CPlannedAssetBaseInfoProcessorResult> {
    
    @Autowired
    private CPlannedAssetBaseInfoRecService service;  
	
    @Override
    public CPlannedAssetBaseInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        
        //调用信息处理公用service
        CPlannedAssetBaseInfoProcessorResult  result = service.execute(acctEntity); 
        
        return result;
    }

}
