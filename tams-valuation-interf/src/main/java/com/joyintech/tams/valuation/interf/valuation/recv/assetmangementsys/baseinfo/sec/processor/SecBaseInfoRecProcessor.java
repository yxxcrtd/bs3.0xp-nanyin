/*
 * 文件名：SecBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.dto.SecBaseInfoRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.service.SecBaseInfoComForInterfService;

/**
 * 基础数据接口--债券
 * 
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, SecBaseInfoRecProcessorResult>{
    
    /**
     * SecBaseInfoComForInterfService
     */
    @Autowired
    private SecBaseInfoComForInterfService secBaseInfoComForInterfService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SecBaseInfoRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
    	// 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        SecBaseInfoRecProcessorResult result = secBaseInfoComForInterfService.execute(acctEntity);
        return result;
    }

}
