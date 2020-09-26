/*
 * 文件名：FundBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.penaltyinterest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PenaltyInterestCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.penaltyinterest.PenaltyInterestCashPlanRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.penaltyinterest.PenaltyInterestRateInfoRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.penaltyinterest.PenaltyInterestBaseInfoRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.penaltyinterest.dto.PenaltyInterestRecResult;
/**
 * 批处理启动文件接收批处理 〈功能详细描述〉
 * 
 * @author zhangquan
 * @version 1.0 2019年12月27日
 * @see PenaltyInterestInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PenaltyInterestInfoService {
 
 
    
    /**
     * 逾期标的基本信息
     */
    @Autowired
    private PenaltyInterestBaseInfoRecService penaltyInterestBaseInfoRecService;
    
    /**
     * 逾期标的现金流计划
     */
    @Autowired
    private PenaltyInterestCashPlanRecService penaltyInterestCashPlanRecService;
    
    /**
     *逾期标的利率信息
     */
    @Autowired
    private PenaltyInterestRateInfoRecService penaltyInterestRateInfoRecService;
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    public PenaltyInterestRecResult execute(TemplateOrderInfoReaderResult acctEntity){
        // 定义返回变量
        PenaltyInterestCommonResult resultCommon = new PenaltyInterestCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        if(TempConstant.逾期标的基本信息.equals(acctEntity.getTmpEName())){
        	resultCommon = penaltyInterestBaseInfoRecService.execute(templateSourceFlgDTO,
                    acctEntity);        
        }else if(TempConstant.逾期标的利率信息.equals(acctEntity.getTmpEName())){
        	resultCommon = penaltyInterestRateInfoRecService.execute(templateSourceFlgDTO,
                    acctEntity);   
        }
        else if(TempConstant.逾期标的现金流计划.equals(acctEntity.getTmpEName())){
            resultCommon = penaltyInterestCashPlanRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        /**add by dongjun on 20181127 start**/
        resultCommon.setParamMap(paramMap);
        return new PenaltyInterestRecResult(resultCommon);
    }
}
