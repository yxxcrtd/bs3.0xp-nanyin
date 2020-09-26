package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.expecteddeposit.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.expecteddeposit.dto.ExpectedProdDealProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.expecteddeposit.service.ExpectedProdDealRecService;
/**
 * 
 * 预期型产品交易信息Processor
 * @author wangqidong
 * @version 1.0 2018年10月9日
 * @see ExpectedProdDealRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExpectedProdDealRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, ExpectedProdDealProcessorResult>{

    @Autowired
    private ExpectedProdDealRecService expectedProdDealRecService;
    
    @Override
    public ExpectedProdDealProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        //用于去重数据
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        //调用预期型交易信息处理公用service
        ExpectedProdDealProcessorResult result = expectedProdDealRecService.execute(acctEntity);
        return result;
    }

}
