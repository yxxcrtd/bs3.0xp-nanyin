/*
 * 文件名：SecBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec.SecBaseInfoRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec.SecRateAdjustmentInfoRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec.SecRepaymentInterestPlan;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec.SecRepaymentInterestRule;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec.SecVestPlanService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.dto.SecBaseInfoRecProcessorResult;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecBaseInfoComForInterfService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecBaseInfoComForInterfService {
    /**
     * 债券基础信息
     */
    @Autowired
    private SecBaseInfoRecService secBaseInfoRecService;
    /**
     * 债券利率调整信息
     */
    @Autowired
    private SecRateAdjustmentInfoRecService secRateAdjustmentInfoRecService;
    /**
     * 债券还本付息计划
     */
    @Autowired
    private SecRepaymentInterestPlan secRepaymentInterestPlan;
    /**
     * 债券还本付息规则
     */
    @Autowired
    private SecRepaymentInterestRule secRepaymentInterestRule;
    /**
     * 债券行权计划
     */
    @Autowired
    private SecVestPlanService secVestPlanService;
    
    public SecBaseInfoRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
        SecBaseInfoRecProcessorRecCommonResult resultCommon = new SecBaseInfoRecProcessorRecCommonResult();
        
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());

        if (TempConstant.债券基础信息.equals(acctEntity.getTmpEName())) {
            resultCommon = secBaseInfoRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.债券利率调整信息.equals(acctEntity.getTmpEName())) {
            resultCommon = secRateAdjustmentInfoRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.债券还本付息规则.equals(acctEntity.getTmpEName())) {
            resultCommon = secRepaymentInterestRule.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.债券还本付息计划.equals(acctEntity.getTmpEName())) {
            resultCommon = secRepaymentInterestPlan.execute(templateSourceFlgDTO, acctEntity);
        }
        else if(TempConstant.债券行权计划.equals(acctEntity.getTmpEName())){
        	resultCommon = secVestPlanService.execute(templateSourceFlgDTO, acctEntity);
        }
        return new SecBaseInfoRecProcessorResult(resultCommon);
    }
}
