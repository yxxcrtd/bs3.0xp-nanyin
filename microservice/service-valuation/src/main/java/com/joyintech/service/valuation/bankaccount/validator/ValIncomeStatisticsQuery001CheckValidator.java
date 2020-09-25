/*
 * 文件名：ValIncomeStatisticsQuery001DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.bankaccount.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.bankaccount.dto.ValIncomeStatisticsQuery001Dto;

/**
 * check
 * 
 * @author HuangJian
 * @version 1.0 2018年11月1日
 * @see ValIncomeStatisticsQuery001DtoValidator
 * @since 1.0
 */
@Component(value="serviceValIncomeStatisticsQuery001CheckValidator")
public class ValIncomeStatisticsQuery001CheckValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    public List<ValidMessage> valid(Object value) {
        ValIncomeStatisticsQuery001Dto dto = (ValIncomeStatisticsQuery001Dto)value;
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        
        //开始日应该小于截止日
        if(StringUtil.isNotEmpty(dto.getVdate())){
            if(!(DateUtil.compareDate(dto.getMdate(), dto.getVdate())==1)){
                String msg = this.getMessage("VALINCOMESTATISTICS_E_001");
                validMessageList.add(new ValidMessage(msg));
               
                
            }
            
        }
        return validMessageList;
    }

}
