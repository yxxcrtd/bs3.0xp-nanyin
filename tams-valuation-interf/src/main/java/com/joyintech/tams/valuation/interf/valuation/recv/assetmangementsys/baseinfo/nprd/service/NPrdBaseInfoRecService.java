/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdBaseinfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdBenchmarkDetailTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdClawbackCalcRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdFeeCalPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdFeeInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdFloatCountingRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdFloatFeeDetailTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdFloatFeeTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdOpenPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdOpenRuleTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdPayPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd.NPrdRateAdjustTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.dto.NPrdBaseInfoProcessorResult;

/**
 * 批处理启动文件接收批处理 对净值型基础信息文件系列机型处理，供给Writer使用
 * 
 * @author qiangchengxi
 * @version 1.0 2018年6月22日
 * @see NPrdBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdBaseInfoRecService {

    /**
     * 基础信息 模板处理Service
     */
    @Autowired
    private NPrdBaseinfoTemplateRecService nPrdBaseinfoTemplateRecService;

    /**
     * 开放规则 模板处理Service
     */
    @Autowired
    private NPrdOpenRuleTemplateRecService nPrdOpenRuleTemplateRecService;

    /**
     * 开放计划 模板处理Service
     */
    @Autowired
    private NPrdOpenPlanTemplateRecService nPrdOpenPlanTemplateRecService;

    /**
     * 净值型产品费用基础信息 模板处理Service
     */
    @Autowired
    private NPrdFeeInfoTemplateRecService nPrdFeeInfoTemplateRecService;

    /**
     * 净值型产品浮动管理费计算明细规则 模板处理Service
     */
    @Autowired
    private NPrdFloatFeeDetailTemplateRecService nPrdFloatFeeDetailTemplateRecService;

    /**
     * 净值型产品浮动管理费计提规则 模板处理Service
     */
    @Autowired
    private NPrdFloatFeeTemplateRecService nPrdFloatFeeTemplateRecService;

    /**
     * 净值型产品付费计划 模板处理Service
     */
    @Autowired
    private NPrdPayPlanTemplateRecService nPrdPayPlanTemplateRecService;

    /**
     * 净值型产品费用计提计划 模板处理Service
     */
    @Autowired
    private NPrdFeeCalPlanTemplateRecService nPrdFeeCalPlanTemplateRecService;

    /**
     * 净值型产品费率调整信息 模板处理Service
     */
    @Autowired
    private NPrdRateAdjustTemplateRecService nPrdRateAdjustTemplateRecService;

    /**
     * 净值型产品业绩基准信息 模板处理Service
     */
    @Autowired
    private NPrdBenchmarkDetailTemplateRecService nPrdBenchmarkDetailTemplateRecService;
    
    /**
     *  净值型产品业绩回补规则 模板处理Service
     */
    @Autowired
    private NPrdClawbackCalcRuleTemplateRecService nPrdClawbackCalcRuleTemplateRecService;
    
    /**
     *  净值型产品业绩报酬计算规则 模板处理Service
     */
    @Autowired
    private NPrdFloatCountingRuleTemplateRecService nPrdFloatCountingRuleTemplateRecService;

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public NPrdBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {

        // 定义返回变量
        NPrdBaseInfoValCommonResult rtnResult = new NPrdBaseInfoValCommonResult();

        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        // 净值型产品基础数据模板-产品基础信息
        if (TempConstant.净值型产品基础信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdBaseinfoTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 02-开放规则
        else if (TempConstant.净值型产品开放规则表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdOpenRuleTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 03-开放计划
        else if (TempConstant.净值型产品开放计划表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdOpenPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 04-费用基础信息
        else if (TempConstant.净值型产品费用基础信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdFeeInfoTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 05-浮动管理费计提规则
        else if (TempConstant.净值型产品费用计提规则表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdFloatFeeTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 06-浮动管理费计算明细规则
        else if (TempConstant.净值型产品浮动管理费计算明细规则表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdFloatFeeDetailTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        // 07-付费计划
        else if (TempConstant.净值型产品付费计划表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdPayPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 08-费用计提计划
        else if (TempConstant.净值型产品费用计提计划表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdFeeCalPlanTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 09-费率调整信息
        else if (TempConstant.净值型产品费率调整信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdRateAdjustTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 10-净值型产品业绩基准信息
        else if (TempConstant.净值型产品业绩基准信息表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdBenchmarkDetailTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        //11-净值型产品业绩回补规则
        else if (TempConstant.净值型产品业绩回补规则.equals(acctEntity.getTmpEName())){
        	rtnResult = nPrdClawbackCalcRuleTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        // 12-浮动管理费计算规则
        else if (TempConstant.净值型产品业绩报酬计算规则.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdFloatCountingRuleTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        rtnResult.setParamMap(paramMap);
        return new NPrdBaseInfoProcessorResult(rtnResult);
    }

}
