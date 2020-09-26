/*
 * 文件名：OptTradeSubjectProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：TanXuan
 * 修改时间：2020年01月08日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.opt.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.opt.dto.OptTradeSubjectProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.opt.service.OptTradeSubjectService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.dto.SecBaseInfoRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.service.SecBaseInfoComForInterfService;

/**
 * 期权交易标的信息
 * 
 * @author TanXuan
 * @version 1.0 2020年01月08日
 * @see OptTradeSubjectProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OptTradeSubjectProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, OptTradeSubjectProcessorResult>{
    
    /**
     * OptTradeSubjectService
     */
    @Autowired
    private OptTradeSubjectService optTradeSubjectService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public OptTradeSubjectProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
    	// 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        OptTradeSubjectProcessorResult result = optTradeSubjectService.execute(acctEntity);
        return result;
    }

}
