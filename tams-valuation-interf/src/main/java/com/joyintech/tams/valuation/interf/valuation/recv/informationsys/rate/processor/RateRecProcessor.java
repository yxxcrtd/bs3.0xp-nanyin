package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.dto.RateProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.service.RateRecService;


/**
 * 行情利率信息接口 Processor
 * @author dongxiangyang
 * @version 1.0 2018年7月05日
 * @see RateRecProcessor
 * @since 1.0
 */

@Service
@Scope("prototype")
public class RateRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, RateProcessorResult> {
    
    @Autowired
    private RateRecService  rateRecService;
    
	@Override
	public RateProcessorResult process(InterfNeedOperateEntity item) throws Exception {
		 // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        
        //调用信息处理公用service
        RateProcessorResult  result=rateRecService.execute(acctEntity);
		return result;
	}

}
