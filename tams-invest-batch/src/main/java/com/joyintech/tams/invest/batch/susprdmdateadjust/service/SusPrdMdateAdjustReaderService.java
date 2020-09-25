/*
 * 文件名：ResetBaseRuleInfoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdmdateadjust.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dao.SusPrdMdateAdjustMapper;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.InvestmentCycleEntityDto;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.SusPrdMdateAdjustDto;

/**
 * reader相关业务处理
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class SusPrdMdateAdjustReaderService extends BaseService{

    @Autowired
    private SusPrdMdateAdjustMapper susprdmdateadjustMapper;
    
    /**
     * 获取待处理数据
     */
    public List<InvestmentCycleEntityDto> execute(Map<String, Object> param) {
        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get(BatchConstant.BATCH_INFO);
        if(null==batchExecuteInfo){
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isCycle", DictCodeList.是否_是.getValue());
        paramMap.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        paramMap.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        paramMap.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        paramMap.put("profitType", DictCodeList.收益类型_净值型.getValue());
        paramMap.put("isSus", DictCodeList.是否_是.getValue());
        paramMap.put("continueFlag", DictCodeList.是否_是.getValue());
        paramMap.put("sysDate",  new SimpleDateFormat("yyyy-MM-dd").format(batchExecuteInfo.getExecuteDate()));
        
        List<SusPrdMdateAdjustDto> susPrdMdateAdjustDtoList = susprdmdateadjustMapper.findSusPrdMdateAdjustList(paramMap);
        
        
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
