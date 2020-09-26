/*
 * 文件名：CPlannedAssetBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cplannedinfo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CPlannedAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cplannedinfo.CPlannedAssetBaseInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cplannedinfo.CPlannedAssetPayPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cplannedinfo.CPlannedAssetPayRuleTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cplannedinfo.dto.CPlannedAssetBaseInfoProcessorResult;

/**
/**
  * 计划类货币型-基础数据接口Service
 * @author zhangzhao
 * @version 1.0 2019年1月22日
 * @see CPlannedAssetBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPlannedAssetBaseInfoRecService {
    
	/**
	 * 计划类资产(货币型)基础信息模板处理Service
	 */
	@Autowired
    private CPlannedAssetBaseInfoTemplateRecService cPlannedAssetBaseInfoTemplateRecService;
	
	/**
	 * 计划类资产(货币型)分红规则模板处理Service
	 */
	@Autowired
    private CPlannedAssetPayPlanTemplateRecService cPlannedAssetPayPlanTemplateRecService;
	
	/**
	 * 计划类资产(货币型)分红计划模板处理Service
	 */
	@Autowired
    private CPlannedAssetPayRuleTemplateRecService cPlannedAssetPayRuleTemplateRecService;
	

    /**
     * <一句话功能简述>
     * <功能 详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public CPlannedAssetBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
             
        //定义返回变量
    	CPlannedAssetBaseInfoValCommonResult rtnResult = new CPlannedAssetBaseInfoValCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
       
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getDtoName());
        
        // 01-基础信息
        if(TempConstant.计划类资产货币型基础信息.equals(acctEntity.getTmpEName())){            
            rtnResult = cPlannedAssetBaseInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 02-分红规则
        else if (TempConstant.计划类资产货币型分红规则.equals(acctEntity.getTmpEName())) {
            rtnResult = cPlannedAssetPayRuleTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 03-分红计划
        else if (TempConstant.计划类资产货币型分红计划.equals(acctEntity.getTmpEName())) {
            rtnResult = cPlannedAssetPayPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        rtnResult.setParamMap(paramMap);
        return new CPlannedAssetBaseInfoProcessorResult(rtnResult);
    }
    
}
