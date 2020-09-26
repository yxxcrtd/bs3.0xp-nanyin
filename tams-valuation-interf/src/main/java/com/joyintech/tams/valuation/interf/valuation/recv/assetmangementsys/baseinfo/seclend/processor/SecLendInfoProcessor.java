/*
 * 文件名：SecLendInfoProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.seclend.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.seclend.dto.SecLendInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.seclend.service.SecLendInfoService;

/**
 * 基础数据接口  - 债券借贷Processor
 * @author wangqidong
 * @version 1.0 2018年10月29日
 * @see SecLendInfoProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecLendInfoProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, SecLendInfoProcessorResult> {

    /**
     * secLendInfoService
     */
    @Autowired
    private SecLendInfoService secLendInfoService;
    /**
     * {@inheritDoc}
     */
    @Override
    public SecLendInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        SecLendInfoProcessorResult result = secLendInfoService.execute(acctEntity);
        return result;
    }

    
}
