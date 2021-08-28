/*
 * 文件名：SecBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.stock.processor;


import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.stock.dto.ValStockBaseRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.stock.service.ValStockBaseInfoComForInterfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 基础数据接口--股票
 * 
 * @author yanya
 * @version 1.0 2021-08-27
 * @see ValStockBaseRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValStockBaseRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, ValStockBaseRecProcessorResult>{
    
    /**
     * SecBaseInfoComForInterfService
     */
    @Autowired
    private ValStockBaseInfoComForInterfService valStockBaseInfoComForInterfService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ValStockBaseRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        ValStockBaseRecProcessorResult result = valStockBaseInfoComForInterfService.execute(acctEntity);
        return result;
    }

}
