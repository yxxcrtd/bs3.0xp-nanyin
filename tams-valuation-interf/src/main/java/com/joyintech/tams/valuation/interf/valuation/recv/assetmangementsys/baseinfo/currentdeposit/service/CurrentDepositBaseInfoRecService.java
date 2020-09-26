/*
 * 文件名：CurrentDepositBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.currentdeposit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CurrentDepositCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.currentDeposit.CurrentDepositRateTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.currentdeposit.dto.CurrentDepositBaseInfoProcessorResult;

@Service
@Scope("prototype")
public class CurrentDepositBaseInfoRecService {
	/**
	 * 活期存款利率 模板service
	 */
	@Autowired
	private CurrentDepositRateTemplateRecService currentDepositRateTemplateRecService;
	

	public CurrentDepositBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
	       //定义返回变量
		CurrentDepositCommonResult rtnResult = new CurrentDepositCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        // 01-活期存款利率
        if(TempConstant.活期存款利率信息表.equals(acctEntity.getTmpEName())){            
            rtnResult = currentDepositRateTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        return new CurrentDepositBaseInfoProcessorResult(rtnResult);
   }
}
