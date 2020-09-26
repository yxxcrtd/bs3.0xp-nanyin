package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.entity.StandardInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.service.StandardInfoRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.service.StandardInfoRecjxService;

@Service
@Scope("prototype")
public class StandardInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, StandardInfoProcessorResult> {
	
	@Autowired
	private StandardInfoRecService standardInfoRecService;
	@Autowired
	private StandardInfoRecjxService standardInfoRecjxService;

	@Override
	public StandardInfoProcessorResult process(InterfNeedOperateEntity item) throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        StandardInfoProcessorResult result=standardInfoRecService.execute(acctEntity);
        //几许传数
        standardInfoRecjxService.prodInfoJx(result);
        
        return  result;
	}
	
}

