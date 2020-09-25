/*
 * 文件名：ValAssetReclassification001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：leipeng
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001ValidDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.service.ValAssetReclassification001ValidService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author leipeng
 * @version 1.0 2018年10月25日
 * @see ValAssetReclassification001Validator
 * @since 1.0
 */
@Transactional
@Component(value="serviceValAssetReclassification001Validator")
public class ValAssetReclassification001Validator implements BeanValidator{
    
    
    @Autowired
    private ValAssetReclassification001ValidService valAssetReclassification001ValidService;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        
        ValAssetReclassification001ValidDto dto = (ValAssetReclassification001ValidDto) value;

        //错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        //判断当前流程是否存在
        if(valAssetReclassification001ValidService.checkBusinessDataInFlow(dto)){
            //流程存在情况
            errorList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",dto.getBooksetId())));
        }

        return errorList;
    }
    

}
