/*
 * 文件名：CurrentDepositProcessorRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.dto.CurrentDepositProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.service.CurrentDepositRecService;

/**
 * 活期存款利息-交易数据接口 processor
 * 
 * @author chendijun
 * @version 1.0 2018年8月21日
 * @see CurrentDepositRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CurrentDepositRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, CurrentDepositProcessorResult>{
    
    @Autowired
    private CurrentDepositRecService currentDepositRecService;
    @Override
    public CurrentDepositProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // TODO Auto-generated method stub
     // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult) item;
        /**add by chendijun on 20180821 start remark:用于去重数据**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        /**add by chendijun on 20180821 end**/
        
        CurrentDepositProcessorResult result = currentDepositRecService.execute(acctEntity);
        return result;
    }




}
