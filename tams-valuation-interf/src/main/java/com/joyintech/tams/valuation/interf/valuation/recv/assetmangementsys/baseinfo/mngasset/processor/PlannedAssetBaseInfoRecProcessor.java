/*
 * 文件名：PlannedAssetBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.entity.PlannedAssetBaseInfoForInterfRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.service.PlannedAssetBaseInfoComForInterfService;

@Service
@Scope("prototype")
public class PlannedAssetBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, PlannedAssetBaseInfoForInterfRecProcessorResult> {

    
    @Autowired
    private PlannedAssetBaseInfoComForInterfService plannedAssetBaseInfoComForInterfService;   
    
    @Override
    public PlannedAssetBaseInfoForInterfRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        
        //调用计划类资产基础信息处理公用service
        PlannedAssetBaseInfoForInterfRecProcessorResult  result = plannedAssetBaseInfoComForInterfService.execute(acctEntity); 
        
        return result;
    }

}
