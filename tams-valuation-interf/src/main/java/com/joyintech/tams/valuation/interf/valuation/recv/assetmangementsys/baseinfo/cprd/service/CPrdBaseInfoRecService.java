/*
 * 文件名：CPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cprd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdBaseinfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdClawbackCalcRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdFeeCalPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdFeeInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdFloatCalcRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdFloatFeeDetailTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdFloatFeeTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdOpenPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdPayPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdPerformanceBenchmarkInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd.CPrdRateAdjustTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cprd.dto.CPrdBaseInfoProcessorResult;

@Service
@Scope("prototype")
public class CPrdBaseInfoRecService {
	
	/**
	 * 货币型产品基础信息 模板处理Service
	 */
	@Autowired
	private CPrdBaseinfoTemplateRecService cPrdBaseinfoTemplateRecService;
	
    
    /**
     * 货币型产品开放计划 模板处理Service
     */
    @Autowired
    private CPrdOpenPlanTemplateRecService cPrdOpenPlanTemplateRecService;
    
    /**
     * 货币型产品费用基础信息 模板处理Service
     */
    @Autowired
    private CPrdFeeInfoTemplateRecService cPrdFeeInfoTemplateRecService;

    /**
     * 货币型产品浮动管理费计算明细规则 模板处理Service
     */
    @Autowired
    private CPrdFloatFeeDetailTemplateRecService cPrdFloatFeeDetailTemplateRecService;

    /**
     * 货币型产品浮动管理费计提规则 模板处理Service
     */
    @Autowired
    private CPrdFloatFeeTemplateRecService cPrdFloatFeeTemplateRecService;

    /**
     * 货币型产品付费计划 模板处理Service
     */
    @Autowired
    private CPrdPayPlanTemplateRecService cPrdPayPlanTemplateRecService;
    
    /**
     * 货币型产品费用计提计划 模板处理Service
     */
    @Autowired
    private CPrdFeeCalPlanTemplateRecService cPrdFeeCalPlanTemplateRecService;

    /**
     * 货币型产品费率调整信息 模板处理Service
     */
    @Autowired
    private CPrdRateAdjustTemplateRecService cPrdRateAdjustTemplateRecService;
    
    
    /**
     * 货币型产品业绩基准信息 模板处理Service
     */
    @Autowired
    private CPrdPerformanceBenchmarkInfoTemplateRecService cPrdPerformanceBenchmarkInfoTemplateRecService;
    
    /**
     * 货币型产品业绩报酬计算规则 模板处理Service
     */
    @Autowired
    private  CPrdFloatCalcRuleTemplateRecService cPrdFloatCalcRuleTemplateRecService;
    /**
     * 货币型产品业绩回补规则 模板处理Service
     */
    @Autowired
    private CPrdClawbackCalcRuleTemplateRecService cPrdClawbackCalcRuleTemplateRecService;
    

	public CPrdBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
	       //定义返回变量
        CPrdBaseInfoValCommonResult rtnResult = new CPrdBaseInfoValCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
                
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        // 01-产品基础信息
        if(TempConstant.货币型产品基础信息表.equals(acctEntity.getTmpEName())){            
            rtnResult = cPrdBaseinfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 03-开放计划
        else if (TempConstant.货币型产品开放计划表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdOpenPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 04-费用基础信息
        else if (TempConstant.货币型产品费用基础信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdFeeInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 05-浮动管理费计提规则
        else if (TempConstant.货币型产品费用计提规则.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdFloatFeeTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 06-浮动管理费计算明细规则
        else if (TempConstant.货币型产品浮动管理费计算明细规则表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdFloatFeeDetailTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 07-付费计划
        else if (TempConstant.货币型产品付费计划表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdPayPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 08-费用计提计划
        else if (TempConstant.货币型产品费用计提计划表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdFeeCalPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 09-费率调整信息
        else if (TempConstant.货币型产品费率调整信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdRateAdjustTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 10-业绩基准信息
        else if (TempConstant.货币型产品业绩基准信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdPerformanceBenchmarkInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        //11-业绩回补规则
        else if (TempConstant.货币型产品业绩回补规则.equals(acctEntity.getTmpEName())){
        	rtnResult = cPrdClawbackCalcRuleTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 12-浮动管理费计算规则
        else if (TempConstant.货币型产品业绩报酬计算规则.equals(acctEntity.getTmpEName())) {
            rtnResult = cPrdFloatCalcRuleTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        rtnResult.setParamMap(paramMap);
        return new CPrdBaseInfoProcessorResult(rtnResult);
	}

}
