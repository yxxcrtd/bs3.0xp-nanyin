package com.joyintech.tams.disclosure.information.config.investmentInfo.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InvestmentInfoValidator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object o) {
        InvestmentInfo001Dto dto = (InvestmentInfo001Dto)o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        return errorList;
    }
}
