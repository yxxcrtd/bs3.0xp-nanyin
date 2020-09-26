/*
 * 文件名：YPrdBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.yprd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.ValPrdFloatCalcRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.ValPrdFloatFeeDetailTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.ValPrdFloatFeeTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdAccrualPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdBaseinfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdFeeCalPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdFeeInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdOpenPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdPayPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdPayRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdPriceAdjustInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd.YPrdRateAdjustTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.yprd.dto.YPrdBaseInfoProcessorResult;

/**
 * 预期型产品接口处理Service
 * 
 * @author dongjun
 * @version 1.0 2018年10月8日
 * @see YPrdBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdBaseInfoRecService {
    
    @Autowired
    private YPrdBaseinfoTemplateRecService yPrdBaseinfoTemplateRecService;
    
    @Autowired
    private YPrdFeeInfoTemplateRecService yPrdFeeInfoTemplateRecService;
    
    @Autowired
    private YPrdPayPlanTemplateRecService yPrdPayPlanTemplateRecService;
    
    @Autowired
    private YPrdRateAdjustTemplateRecService yPrdRateAdjustTemplateRecService;
    
    @Autowired
    private YPrdOpenPlanTemplateRecService yPrdOpenPlanTemplateRecService;
    
    @Autowired
    private YPrdPayRuleTemplateRecService yPrdPayRuleTemplateRecService;
    
    @Autowired
    private YPrdAccrualPlanTemplateRecService yPrdAccrualPlanTemplateRecService;
    
    @Autowired
    private YPrdFeeCalPlanTemplateRecService yPrdFeeCalPlanTemplateRecService;
    
    @Autowired
    private YPrdPriceAdjustInfoTemplateRecService yPrdPriceAdjustInfoTemplateRecService;
    
    @Autowired
    private ValPrdFloatFeeTemplateRecService valPrdFloatFeeTemplateRecService;
    
    @Autowired
    private ValPrdFloatFeeDetailTemplateRecService valPrdFloatFeeDetailTemplateRecService;
    
    @Autowired
    private ValPrdFloatCalcRuleTemplateRecService valPrdFloatCalcRuleTemplateRecService;
   
    public YPrdBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
        
        YPrdBaseInfoValCommonResult rtnResult = new YPrdBaseInfoValCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
                
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        // 01-产品基础信息
        if(TempConstant.预期收益型产品基础信息表.equals(acctEntity.getTmpEName())){            
            rtnResult = yPrdBaseinfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 02-产品费用基础信息
        else if (TempConstant.预期收益型产品费用基础信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = yPrdFeeInfoTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 03-预期收益型产品付费计划表
        else if(TempConstant.预期收益型产品付费计划表.equals(acctEntity.getTmpEName())){
            rtnResult = yPrdPayPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 04-预期收益型产品费率调整信息表
        else if(TempConstant.预期收益型产品费率调整信息表.equals(acctEntity.getTmpEName())){
            rtnResult = yPrdRateAdjustTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //05-预期收益型产品开发计划
        else if (TempConstant.预期收益型产品开放计划.equals(acctEntity.getTmpEName())){
        	rtnResult = yPrdOpenPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //06预期收益型产品付息规则
        else if (TempConstant.预期收益型产品付息规则.equals(acctEntity.getTmpEName())){
        	rtnResult = yPrdPayRuleTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }  
        //07预期收益型产品付息计划
        else if (TempConstant.预期收益型产品付息计划.equals(acctEntity.getTmpEName())){
        	rtnResult = yPrdAccrualPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //08预期收益型产品费用计提计划
        else if (TempConstant.预期收益型产品费用计提计划.equals(acctEntity.getTmpEName())){
        	rtnResult = yPrdFeeCalPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //09预期收益型产品产品报价调整信息
        else if (TempConstant.预期收益型产品产品报价调整信息.equals(acctEntity.getTmpEName())){
        	rtnResult = yPrdPriceAdjustInfoTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //10浮动管理费计提规则
        else if (TempConstant.预期收益型产品费用计提规则.equals(acctEntity.getTmpEName())){
        	rtnResult = valPrdFloatFeeTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //11预期收益型产品浮动管理费计算明细规则
        else if (TempConstant.预期收益型产品浮动管理费计算明细规则.equals(acctEntity.getTmpEName())){
        	rtnResult = valPrdFloatFeeDetailTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //12-浮动管理费计算规则
        else if (TempConstant.预期收益型产品业绩报酬计算规则.equals(acctEntity.getTmpEName())){
        	rtnResult = valPrdFloatCalcRuleTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        rtnResult.setParamMap(paramMap);
       
        return new YPrdBaseInfoProcessorResult(rtnResult);
    }
}
