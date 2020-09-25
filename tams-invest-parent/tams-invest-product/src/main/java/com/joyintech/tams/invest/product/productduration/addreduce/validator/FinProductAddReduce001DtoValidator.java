package com.joyintech.tams.invest.product.productduration.addreduce.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce001Dto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: 撤销校验
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class FinProductAddReduce001DtoValidator implements BeanValidator {

    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductAddReduce001Dto dto = (FinProductAddReduce001Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        String sysDate = platformDateImpl.getSysDateStr();
        return errorList;
    }
}
