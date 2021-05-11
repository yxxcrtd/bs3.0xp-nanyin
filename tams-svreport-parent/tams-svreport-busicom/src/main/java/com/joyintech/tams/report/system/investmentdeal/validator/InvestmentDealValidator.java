package com.joyintech.tams.report.system.investmentdeal.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvestmentDealValidator implements BeanValidator {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @Override
    public List<ValidMessage> valid(Object o) {
        InvestmentDealInfoDto dto=(InvestmentDealInfoDto)o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "InvestmentDealInfo002", null, ValidMessage.class);
        return resultList;
    }
}
