/*
 * 文件名：ResetBaseRuleInfoProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdmdateadjust.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.InvestmentCycleEntityDto;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.SusPrdMdateAdjustDto;

/**
 * processor相关业务处理
 * 2019年9月26日
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class SusPrdMdateAdjustProcessorService extends BaseService{

    /**
     * process 数据处理
     */
    public List<InvestmentCycleEntityDto> execute(List<SusPrdMdateAdjustDto> susPrdMdateAdjustDtoList) {
    	
    	HashSet<String> investmentCycleSet = new HashSet<String>();
		List<InvestmentCycleEntityDto> invertEntityList = new ArrayList<InvestmentCycleEntityDto> ();
		
		// InvestmentCycle set集合去重
		for(SusPrdMdateAdjustDto susPrdMdateAdjustDto : susPrdMdateAdjustDtoList) {
			investmentCycleSet.add(susPrdMdateAdjustDto.getInvestmentCycle());
		}
		
		for(String investmentCycle : investmentCycleSet) {
			
			// 投资周期entity
			InvestmentCycleEntityDto investmentCycleEntityDto = new InvestmentCycleEntityDto();
			List<SusPrdMdateAdjustDto> susPrdList = new ArrayList<SusPrdMdateAdjustDto> ();
			
			for(int i = 0 ; i < susPrdMdateAdjustDtoList.size() ; i++ ) {
				
				SusPrdMdateAdjustDto susPrdMdateAdjustDto = susPrdMdateAdjustDtoList.get(i);
				
				if(investmentCycle.equals(susPrdMdateAdjustDto.getInvestmentCycle())) { // 去重的投资日期investmentCycle与原list中的investmentCycle相等
					investmentCycleEntityDto.setInvestmentCycle(investmentCycle);
					susPrdList.add(susPrdMdateAdjustDto);
				}
				
				if(i + 1 == susPrdMdateAdjustDtoList.size()) { // 循环到了最后一条记录
					investmentCycleEntityDto.setSusprdmdateadjustdtoList(susPrdList);
				}
			}
			invertEntityList.add(investmentCycleEntityDto); // 组装完成一个投资周期entity
		}
		
		return invertEntityList;
    }
    
}
