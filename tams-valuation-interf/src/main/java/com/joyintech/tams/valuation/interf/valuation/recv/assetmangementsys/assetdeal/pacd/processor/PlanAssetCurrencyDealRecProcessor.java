/*
 * 文件名：PlanAssetCurrencyDealRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.pacd.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.pacd.dto.PlanAssetCurrencyDealRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.pacd.service.PlanAssetCurrencyDealRecService;

/**
 * 计划类资产（货币型）交易数据接口
 * @author HuangJian
 * @version 1.0 2019年1月23日
 * @see PlanAssetCurrencyDealRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlanAssetCurrencyDealRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, PlanAssetCurrencyDealRecProcessorResult>{

    

    @Autowired
    private PlanAssetCurrencyDealRecService planAssetCurrencyDealRecService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PlanAssetCurrencyDealRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // TODO Auto-generated method stub
        

        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        PlanAssetCurrencyDealRecProcessorResult  result =planAssetCurrencyDealRecService.execute(acctEntity);
        return result;
    }

}
