/*
 * 文件名：SecLendInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.seclend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecLendInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.seclend.ValSeclendInfoService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.seclend.ValSeclendMortgageService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.seclend.dto.SecLendInfoProcessorResult;

/**
 * 债券借贷service
 * 
 * @author wangqidong
 * @version 1.0 2018年10月29日
 * @see SecLendInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecLendInfoService {

    /**
     * 债券借贷标的信息
     */
    @Autowired
    private ValSeclendInfoService valSeclendInfoService;

    /**
     * 债券借贷质押债券信息
     */
    @Autowired
    private ValSeclendMortgageService valSeclendMortgageService;

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param acctEntity acctEntity
     * @return SecLendInfoProcessorResult
     * @see
     * @since 1.0
     */
    public SecLendInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        SecLendInfoCommonResult resultCommon =  new SecLendInfoCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        if (TempConstant.债券借贷标的信息.equals(acctEntity.getTmpEName())) {
            resultCommon = valSeclendInfoService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.债券借贷质押债券信息.equals(acctEntity.getTmpEName())) {
            resultCommon = valSeclendMortgageService.execute(templateSourceFlgDTO,
                acctEntity);
        }      
        return new SecLendInfoProcessorResult(resultCommon);
    }

}
