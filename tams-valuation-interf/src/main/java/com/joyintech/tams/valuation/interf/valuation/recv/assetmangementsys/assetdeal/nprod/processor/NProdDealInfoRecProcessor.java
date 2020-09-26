/*
 * 文件名：NProdDealInfoRecRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.nprod.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.nprod.dto.NProdDealInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.nprod.service.NProdDealInfoRecService;

/**
 * 净值型产品交易接口 Processor
 * @author weipeng
 * @version 1.0 2018年6月25日
 * @see NProdDealInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NProdDealInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, NProdDealInfoProcessorResult> {
    
    @Autowired
    private NProdDealInfoRecService nProdDealInfoRecService;   
    
    @Override
    public NProdDealInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        /**add by wujie on 20180720 start remark:用于去重数据**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());//modify by dongjun on 20190104
        /**add by wujie on 20180720 end**/
        //调用净值型交易信息处理公用service
        NProdDealInfoProcessorResult  result = nProdDealInfoRecService.execute(acctEntity); 
        
        return result;
    }
}
