/*
 * 文件名：VatDealInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.processor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.dto.VatDealInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.services.VatDealInfoRecService;

/**
 * 
 * 增值税交易   接口Processor
 * @author wangqidong
 * @version 1.0 2018年8月16日
 * @see VatDealInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class VatDealInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, VatDealInfoProcessorResult>{

    /**
     * 参数
     */
    @SuppressWarnings("unused")
	private Map<String, Object> param;

    /**
     * 设置 param
     * 
     * @param param param
     */
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
    
    /**
     * vatService
     */
    @Autowired
    private VatDealInfoRecService vatService;

    /**
     * {@inheritDoc}
     */
    @Override
    public VatDealInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        //去重数据
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        //调用增值税交易信息处理公用service
        VatDealInfoProcessorResult result = vatService.execute(acctEntity);
        
        return result;
    }
     
}




