package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.accountamount.service.AccountAmount001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AssetInvestmentDetailValidator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object o) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        return null;
    }
}
