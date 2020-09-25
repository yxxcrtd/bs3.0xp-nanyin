/*
 * 文件名：ResetBaseRuleInfoProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.susprdmdateadjust.processor;

import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.InvestmentCycleEntityDto;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.InvestmentCycleEntityDto2;

/**
 * 对应 逻辑处理
 * 
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Service
public class SusPrdMdateAdjustProcessor extends BaseProcessor<NeedOperateEntity, InvestmentCycleEntityDto2> {

	
	@Override
	public InvestmentCycleEntityDto2 process(NeedOperateEntity item) throws Exception {

		// 获取reader方法的返回值
		InvestmentCycleEntityDto investmentCycleEntityDto = (InvestmentCycleEntityDto) item;
		InvestmentCycleEntityDto2 investmentCycleEntityDto2 = new InvestmentCycleEntityDto2();
		investmentCycleEntityDto2.setInvestmentCycleEntityDto(investmentCycleEntityDto);
		
		return investmentCycleEntityDto2;
	}
}
