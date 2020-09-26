/*
 * 文件名：CurrentDepositBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.currentdeposit.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.currentdeposit.dto.CurrentDepositBaseInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.currentdeposit.service.CurrentDepositBaseInfoRecService;

@Service
@Scope("prototype")
public class CurrentDepositBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, CurrentDepositBaseInfoProcessorResult> {
   
	/**
    * CurrentDepositBaseInfoRecService
    */
	@Autowired
	private  CurrentDepositBaseInfoRecService currentDepositBaseInfoRecService;
	
	@Override
	public CurrentDepositBaseInfoProcessorResult process(InterfNeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值(processor并发数据结构)
		TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult) item;
		
		//调用活期存款利率公用servie
		CurrentDepositBaseInfoProcessorResult result = currentDepositBaseInfoRecService.execute(acctEntity);
		return result;
	}

}
