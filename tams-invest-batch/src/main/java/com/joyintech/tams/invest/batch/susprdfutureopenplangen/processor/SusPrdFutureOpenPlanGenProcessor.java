/*
 * 文件名：ResetBaseRuleInfoProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.susprdfutureopenplangen.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.FinOpenPlanDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.service.BussFinProductOpenPlanService;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.service.SusPrdFutureOpenPlanGenProcesserService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 对应 逻辑处理
 * 
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SusPrdFutureOpenPlanGenProcessor extends BaseProcessor<NeedOperateEntity, FinOpenPlanDto> {

	@Autowired
	private BussFinProductOpenPlanService bussfinproductopenplanService;

	@Autowired
	private SusPrdFutureOpenPlanGenProcesserService susPrdFutureOpenPlanGenProcesserService;

	
	@Override
	public FinOpenPlanDto process(NeedOperateEntity item) throws Exception {

		int years = Integer.parseInt(CacheUtil.getSysParam(BussinessConstant.MAX_AFTER_NOW_YEAR));
		
		// 获取reader方法的返回值
		SusPrdFutureOpenPlanGenDto susprdfutureopenplangendto = (SusPrdFutureOpenPlanGenDto) item;

		// 公共方法生成的List<开放计划>
		List<FinOpenPlan> finOpenPlanList = bussfinproductopenplanService
				.genOpenPlanList(susPrdFutureOpenPlanGenProcesserService.executeDto(susprdfutureopenplangendto, years));

		// 更新后的开放计划
		List<FinOpenPlan> newFinOpenPlanList = susPrdFutureOpenPlanGenProcesserService.executeList(finOpenPlanList,
				susprdfutureopenplangendto);
		
		FinOpenPlanDto finOpenPlanDto = new FinOpenPlanDto();
		finOpenPlanDto.setFinOpenPlanList(newFinOpenPlanList);
		finOpenPlanDto.setSusPrdFutureOpenPlanGenDto(susprdfutureopenplangendto);
		return finOpenPlanDto;
	}

}
