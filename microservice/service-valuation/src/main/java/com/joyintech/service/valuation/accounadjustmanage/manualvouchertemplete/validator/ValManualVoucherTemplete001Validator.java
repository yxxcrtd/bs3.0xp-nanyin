/*
 * 文件名：ValVoucherQuery001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 手工凭证删除校验
 * @author sunshuaishuai
 * @version 1.0 2021年2月24日
 * @see ValManualVoucherTemplete001Validator
 * @since 1.0
 */
@Component(value="serviceValManualVoucherTemplete001Validator")
public class ValManualVoucherTemplete001Validator implements BeanValidator{
    
    @Autowired
    private ValManualVoucherTemplete001Service valManualVoucherTemplete001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {

        ValManualVoucherTemplete001Dto dto = (ValManualVoucherTemplete001Dto) value;

        //错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        //判断当前流程是否存在
        if(valManualVoucherTemplete001Service.checkBusinessDataInFlow(dto)){
            //流程存在情况
            errorList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",dto.getTmpCode())));
        }

        return errorList;
    }
    
}
