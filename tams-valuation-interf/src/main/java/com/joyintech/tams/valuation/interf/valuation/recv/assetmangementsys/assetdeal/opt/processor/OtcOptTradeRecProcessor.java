/*
 * 文件名：OtcOptTradeRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2020年01月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.opt.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.opt.dto.OtcOptTradeProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.opt.service.OtcOptTradeRecService;

/**
 * 场外期权交易 接口 Processor
 * 
 * @author tanxuan
 * @version 1.0 2020年01月10日
 * @see OtcOptTradeRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OtcOptTradeRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, OtcOptTradeProcessorResult> {

    @Autowired
    private OtcOptTradeRecService recService;   
    
    @Override
    public OtcOptTradeProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        
        /**add by chufengjie on 20180720 start remark:用于去重数据**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        /**add by chufengjie on 20180720 end**/  
        //调用债券交易信息处理公用service
        OtcOptTradeProcessorResult  result = recService.execute(acctEntity); 
        
        return result;
    }

}
