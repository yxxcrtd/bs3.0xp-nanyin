/*
 * 文件名：NetValuationDataProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.processor;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetProdValuationDataResult;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.service.NetProdValuationDataService;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendWriterEntity;

/**
 * 净值型产品估值数据Processor
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetValuationDataProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NetProdValuationDataProcessor extends InterfBaseProcessor<SendReaderEntity, SendWriterEntity>{

	/**
	 * 净值型产品估值数据Service
	 */
	@Autowired
    private NetProdValuationDataService netProdValuationDataService;
	
	/**
	 * process处理方法
	 */
	@Override
	public SendWriterEntity process(SendReaderEntity item) throws Exception {
		// TODO Auto-generated method stub
		ValAddTaxFlowprocessorResult netValuationDataResult = new ValAddTaxFlowprocessorResult();
		netValuationDataResult = netProdValuationDataService.execute(item);
		return netValuationDataResult;
	}

}
