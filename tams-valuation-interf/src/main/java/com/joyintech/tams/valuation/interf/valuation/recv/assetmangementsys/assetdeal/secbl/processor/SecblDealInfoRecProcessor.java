package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.secbl.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.secbl.dto.SecblDealInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.secbl.service.SecblDealInfoRecService;

/**
 * 
 * 债券借贷交易信息 接口 Processor
 * @author dongxiangyang
 * @version 1.0 2018年6月28日
 * @see SecblDealInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecblDealInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, SecblDealInfoProcessorResult>{

	@Autowired
    private SecblDealInfoRecService recService;

	@Override
	public SecblDealInfoProcessorResult process(InterfNeedOperateEntity item) throws Exception {
		
		 // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        //调用债券交易信息处理公用service
        SecblDealInfoProcessorResult  result = recService.execute(acctEntity); 
        
        return result;
	}   
	
	
	
}
