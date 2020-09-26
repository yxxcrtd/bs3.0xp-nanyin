package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.feedeal.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.feedeal.dto.FeeDealProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.feedeal.service.FeeDealRecService;
/**
 * 
 * 费用交易Processor
 * @author huwei
 * @version 1.0 2018年10月16日
 * @see FeeDealRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FeeDealRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, FeeDealProcessorResult>{

    @Autowired
    private FeeDealRecService feeDealRecService;
    
    @Override
    public FeeDealProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        //用于去重数据
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        //调用费用交易处理公用service
        FeeDealProcessorResult result = feeDealRecService.execute(acctEntity);
        return result;
    }

}
