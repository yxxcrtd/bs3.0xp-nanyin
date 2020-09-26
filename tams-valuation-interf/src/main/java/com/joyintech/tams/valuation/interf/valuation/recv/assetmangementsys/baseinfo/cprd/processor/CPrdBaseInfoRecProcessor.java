/*
 * 文件名：CPrdBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cprd.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cprd.dto.CPrdBaseInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cprd.service.CPrdBaseInfoRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cprd.service.CPrdBaseInfoRecjxService;

/**
 * 货币型产品产品信息接口 Processor 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年8月13日
 * @see CPrdBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPrdBaseInfoRecProcessor
		extends InterfBaseProcessor<InterfNeedOperateEntity, CPrdBaseInfoProcessorResult> {

	@Autowired
	private CPrdBaseInfoRecService cPrdBaseInfoRecService;
	
	@Autowired
	private CPrdBaseInfoRecjxService cPrdBaseInfoRecjxService;

	@Override
	public CPrdBaseInfoProcessorResult process(InterfNeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值(processor并发数据结构)
		TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult) item;

		// 调用货币型基础信息处理公用service
		CPrdBaseInfoProcessorResult result = cPrdBaseInfoRecService.execute(acctEntity);
        //几许传数
		cPrdBaseInfoRecjxService.prodInfoJx(result);

		return result;
	}

}
