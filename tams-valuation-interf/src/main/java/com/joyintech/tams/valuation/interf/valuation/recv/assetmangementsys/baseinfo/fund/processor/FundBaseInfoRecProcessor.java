/*
 * 文件名：FundBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.fund.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.fund.dto.FundBaseInfoForInterfRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.fund.service.FundBaseInfoComForInterfService;

/**
 * 基金类资产 FundBaseInfoRecProcessor
 * 
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see FundBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, FundBaseInfoForInterfRecProcessorResult> {
    /**
     * FundBaseInfoComForInterfService
     */
    @Autowired
    private FundBaseInfoComForInterfService fundBaseInfoComForInterfService;

    @Override
    public FundBaseInfoForInterfRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {

        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        // 调用基金类资产基础信息处理公用service
        FundBaseInfoForInterfRecProcessorResult result = fundBaseInfoComForInterfService.execute(
            acctEntity);
        return result;
    }
}
