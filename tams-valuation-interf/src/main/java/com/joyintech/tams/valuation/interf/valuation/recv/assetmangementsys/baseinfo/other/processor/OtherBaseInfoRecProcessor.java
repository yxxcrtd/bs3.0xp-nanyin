/*
 * 文件名：OtherBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.other.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.other.entity.OtherBaseInfoForAssetProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.other.service.OtherBaseInfoRecService;

@Service
@Scope("prototype")
public class OtherBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, OtherBaseInfoForAssetProcessorResult> {

    @Autowired
    private OtherBaseInfoRecService service;
    
    
    @Override
    public OtherBaseInfoForAssetProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        return service.execute(acctEntity);
    }

}
