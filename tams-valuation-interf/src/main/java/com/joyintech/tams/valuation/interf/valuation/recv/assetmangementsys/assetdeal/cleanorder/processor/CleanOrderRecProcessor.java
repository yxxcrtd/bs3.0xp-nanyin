/*
 * 文件名：CleanOrderProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cleanorder.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cleanorder.dto.CleanOrderProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cleanorder.service.CleanOrderRecService;

/**
 * 结算指令接口 Processor
 * 
 * @author chendijun
 * @version 1.0 2018年8月17日
 * @see CleanOrderRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CleanOrderRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity,CleanOrderProcessorResult>{
    
    @Autowired
    private CleanOrderRecService cleanOrderRecService;
    
    @Override
    public CleanOrderProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // TODO Auto-generated method stub
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult) item;
        /**add by chendijun on 20180817 start remark:用于去重数据**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());//modify by dongjun on 20190104
        /**add by chendijun on 20180817 end**/
        CleanOrderProcessorResult result = cleanOrderRecService.execute(acctEntity);
        return result;
    }

}
