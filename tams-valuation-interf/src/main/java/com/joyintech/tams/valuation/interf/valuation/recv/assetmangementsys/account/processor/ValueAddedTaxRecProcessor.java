package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.account.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.account.dto.ValueAddedTaxRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.account.service.ValueAddedTaxRecService;

/**
 * 增值税信息 接口 Processor
 * @author dongxiangyang
 * @version 1.0 2018年6月28日
 * @see ValueAddedTaxRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValueAddedTaxRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, ValueAddedTaxRecProcessorResult> {

	
    @Autowired
      private ValueAddedTaxRecService valueAddedTaxRecService;
	@Override
	public ValueAddedTaxRecProcessorResult process(InterfNeedOperateEntity item) throws Exception {
        
      // 获取reader方法的返回值(processor并发数据结构)
      TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
      InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
      acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
      //调用信息处理公用service
      ValueAddedTaxRecProcessorResult result=valueAddedTaxRecService.excute(acctEntity);
      
      return result;
	}

}
