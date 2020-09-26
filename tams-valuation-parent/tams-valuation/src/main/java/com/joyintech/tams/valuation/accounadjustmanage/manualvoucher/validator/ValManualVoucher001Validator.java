/*
 * 文件名：ValVoucherQuery001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher001Service;


/**
 * 手工凭证删除校验
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValManualVoucher003Validator
 * @since 1.0
 */
@Component
public class ValManualVoucher001Validator implements BeanValidator{
    
    @Autowired
    private ValManualVoucher001Service valManualVoucher001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        
        ValManualVoucher001Dto dto = (ValManualVoucher001Dto) value;

        //错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        //判断当前流程是否存在
        if(valManualVoucher001Service.checkBusinessDataInFlow(dto)){
            //流程存在情况
            errorList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",dto.getSeqno())));
        }

        return errorList;
    }
    
}
