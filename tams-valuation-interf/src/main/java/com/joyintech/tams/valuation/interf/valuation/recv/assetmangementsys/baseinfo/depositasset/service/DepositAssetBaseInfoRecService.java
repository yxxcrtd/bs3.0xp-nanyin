/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.depositasset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.DepositeAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset.DepositeAssetBaseInfoTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset.DepositeAssetCapPlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset.DepositeAssetRateAdjustTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset.DepositeAssetRatePlanTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset.DepositeAssetRepayCapIntTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.depositasset.dto.DepositAssetBaseInfoProcessorResult;

/**
 * 批处理启动文件接收批处理
 * 对存款类资产基础信息文件系列机型处理，供给Writer使用
 * @author qiangchengxi
 * @version 1.0 2018年6月22日
 * @see DepositAssetBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositAssetBaseInfoRecService {
    
    /**
     * 存款类资产-基础信息 模板处理Service
     */
    @Autowired
    private DepositeAssetBaseInfoTemplateRecService depositeAssetBaseInfoTemplateRecService;
    
    /**
     * 存款类资产-利率调整 模板处理Service
     */
    @Autowired
    private DepositeAssetRateAdjustTemplateRecService depositeAssetRateAdjustTemplateRecService;
    
    /**
     * 存款类资产-付息计划 模板处理Service
     */
    @Autowired
    private DepositeAssetRatePlanTemplateRecService depositeAssetRatePlanTemplateRecService;
    
    /**
     * 存款类资产-还本计划 模板处理Service
     */
    @Autowired
    private DepositeAssetCapPlanTemplateRecService depositeAssetCapPlanTemplateRecService;

    
    /**
     * 存款类资产-还本付息规则 模板处理Service
     */
    @Autowired
    private DepositeAssetRepayCapIntTemplateRecService depositeAssetRepayCapIntTemplateRecService;

    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public DepositAssetBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
               
        //定义返回变量
        DepositeAssetBaseInfoValCommonResult rtnResult = new DepositeAssetBaseInfoValCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
                
       
        //01-产品基础信息
        if(TempConstant.存款类资产基础信息.equals(acctEntity.getTmpEName())){            
            rtnResult = depositeAssetBaseInfoTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 02-利率调整
        else if (TempConstant.存款类资产利率调整.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetRateAdjustTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 03-付息计划
        else if (TempConstant.存款类资产付息计划.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetRatePlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 04-还本计划 
        else if (TempConstant.存款类资产还本计划.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetCapPlanTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 05-还本付息规则  
        else if (TempConstant.存款类资产还本付息规则.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetRepayCapIntTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
        }
       
        return new DepositAssetBaseInfoProcessorResult(rtnResult);
    }
    
    

}
