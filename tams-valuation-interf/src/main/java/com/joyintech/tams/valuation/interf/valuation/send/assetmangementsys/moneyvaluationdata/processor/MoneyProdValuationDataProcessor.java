/*
 * 文件名：MoneyProdValuationDataProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.processor;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dto.MoneyProdValuationDataResult;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.service.MoneyProdValuationDataService;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendWriterEntity;

/**
 * 货币型产品估值数据Processor
 * @author chendong
 * @version 1.0 2018年11月19日
 * @see MoneyProdValuationDataProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyProdValuationDataProcessor extends InterfBaseProcessor<SendReaderEntity, SendWriterEntity>{

	@Autowired
    private MoneyProdValuationDataService moneyProdValuationDataService;
	
	/**
	 * process处理方法
	 */
	@Override
	public SendWriterEntity process(SendReaderEntity item) throws Exception {
		// 获取reader方法的返回值(processor并发数据结构)
		ValAddTaxFlowprocessorResult moneyProdValuationDataResultList = moneyProdValuationDataService.execute(item);
		
		return moneyProdValuationDataResultList;
	}
}
