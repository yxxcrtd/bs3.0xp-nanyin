package com.joyintech.tams.invest.riskredis.quota.medianmanagement.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

@Component
public class PltMedianManagementValidator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        errorList.add(new ValidMessage(getMessage("日期不允许为空！")));
        return errorList;
    }
}
