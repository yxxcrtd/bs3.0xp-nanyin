/*
 * 文件名：ValChannelTradeProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.dto.ValChannelTradeProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.service.ValChannelTradeService;


/**
 * 通道产品-交易数据接口 Processor
 * 
 * @author zhangwei
 * @version 1.0 2019年3月12日
 * @see ValChannelTradeProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValChannelTradeProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, ValChannelTradeProcessorResult> {

    @Autowired
    private ValChannelTradeService valChannelTradeService;
    
    @Override
    public ValChannelTradeProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {

        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        // 调用通道产品交易信息处理公用service
        ValChannelTradeProcessorResult result = valChannelTradeService.execute(acctEntity);;
        return result;
    }

}
