/*
 * 文件名：PrdSupplementDealRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2019年2月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.prdclawback.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.prdclawback.dto.PrdSupplementDealRecResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.prdclawback.service.PrdSupplementDealRecService;


/**
 * 产品回补交易Processor
 * 
 * @author dongjun
 * @version 1.0 2019年2月21日
 * @see PrdSupplementDealRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdSupplementDealRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, PrdSupplementDealRecResult> {

    @Autowired
    private PrdSupplementDealRecService prdSupplementDealRecService;

    /**
     * {@inheritDoc}
     */
    @Override
    public PrdSupplementDealRecResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;

        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get(
            "executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());

        // 调用公用service
        PrdSupplementDealRecResult result = prdSupplementDealRecService.execute(acctEntity);

        return result;
    }

}
