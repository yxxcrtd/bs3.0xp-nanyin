/*
 * 文件名：SecBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.stock.service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValStockBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.stock.ValStockBaseRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.sec.dto.SecBaseInfoRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.stock.dto.ValStockBaseRecProcessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yanya
 * @version 1.0 2021-08-27
 * @see ValStockBaseInfoComForInterfService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValStockBaseInfoComForInterfService {
    /**
     * 股票基础信息
     */
    @Autowired
    private ValStockBaseRecService valStockBaseRecService;

    
    public ValStockBaseRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
        ValStockBaseInfoRecProcessorRecCommonResult resultCommon = new ValStockBaseInfoRecProcessorRecCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        if (TempConstant.股票基础信息.equals(acctEntity.getTmpEName())) {
            resultCommon = valStockBaseRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        return new ValStockBaseRecProcessorResult(resultCommon);
    }
}
