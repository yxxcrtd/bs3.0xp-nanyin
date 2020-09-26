/*
 * 文件名：ProfitAndLossAccountingProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.depositasset.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.depositasset.dto.DepositAssetDealInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.depositasset.service.DepositAssetDealInfoRecService;

/**
 * 
 * 存款类资产交易信息 接口 Processor
 * @author weipeng
 * @version 1.0 2018年6月25日
 * @see DepositAssetDealInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositAssetDealInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, DepositAssetDealInfoProcessorResult> {

    
    @Autowired
    private DepositAssetDealInfoRecService recService;   
    
    @Override
    public DepositAssetDealInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        /**add by chufengjie on 20180809 start remark:用于去重数据**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        /**add by chufengjie on 20180809 end**/  
        
        //调用存款类资产交易信息处理公用service
        DepositAssetDealInfoProcessorResult  result = recService.execute(acctEntity); 
        
        return result;
    }

}
