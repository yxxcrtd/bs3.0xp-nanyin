/*
 * 文件名：PlannedAssetBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetBaseInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset.PlannedAssetBaseInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset.PlannedCapPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset.PlannedIntPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset.PlannedRateAdjInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset.PlannedRepayCapIntTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.entity.PlannedAssetBaseInfoForInterfRecProcessorResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 批处理启动文件接收批处理
 * 对计划类资产基础信息文件系列机型处理，供给Writer使用
 * @author ruanhongming
 * @version 1.0 2018年6月22日
 * @see PlannedAssetBaseInfoComForInterfService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedAssetBaseInfoComForInterfService {
    
    /**
     * 基础信息 模板处理Service
     */
    @Autowired
    private PlannedAssetBaseInfoTemplateRecService plannedAssetBaseInfoTemplateRecService;
    
    /**
     * 利率调整信息 模板处理Service
     */
    @Autowired
    private PlannedRateAdjInfoTemplateRecService plannedRateAdjInfoTemplateRecService;
    
    /**
     * 还本付息规则  模板处理Service
     */
    @Autowired
    private PlannedRepayCapIntTemplateRecService plannedRepayCapIntTemplateRecService;
    
    /**
     * 付息计划  模板处理Service
     */
    @Autowired
    private PlannedIntPlanTemplateRecService plannedIntPlanTemplateRecService;
    
    /**
     * 付息计划  模板处理Service
     */
    @Autowired
    private PlannedCapPlanTemplateRecService plannedCapPlanTemplateRecService;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public PlannedAssetBaseInfoForInterfRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
               
        //定义返回变量
        PlannedAssetBaseInfoRecCommonResult rtnResult = new PlannedAssetBaseInfoRecCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());


        //计划类支持（利率型）基础数据模板-产品基础信息
        if(TempConstant.计划类资产利率型基础信息表.equals(acctEntity.getTmpEName())){ 
            rtnResult = plannedAssetBaseInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 02-利率调整信息
        else if(TempConstant.计划类资产利率型利率调整信息表.equals(acctEntity.getTmpEName())){
            rtnResult =plannedRateAdjInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 03-还本付息规则
        else if(TempConstant.计划类资产利率型还本付息规则表.equals(acctEntity.getTmpEName())){
            rtnResult = plannedRepayCapIntTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 04-付息计划
        else if(TempConstant.计划类资产利率型付息计划表.equals(acctEntity.getTmpEName())){
            rtnResult = plannedIntPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
       // 04-还本计划
        else if(TempConstant.计划类资产利率型还本计划表.equals(acctEntity.getTmpEName())){
            rtnResult = plannedCapPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }

        /**add by wujie on 20210507 start**/
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        rtnResult.setParamMap(paramMap);
        /**add by wujie on 20210507 end**/
        return new PlannedAssetBaseInfoForInterfRecProcessorResult(rtnResult);
    }
    
    

}
