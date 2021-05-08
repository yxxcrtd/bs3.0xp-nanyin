package com.joyintech.tams.report.system.mstbond.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.system.disrelorg.dto.DisRelOrgInfo001Dto;
import com.joyintech.tams.report.system.mstbond.dto.MstBondCreditRatingDto;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MstBondCreditRatingValidator implements BeanValidator {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @Override
    public List<ValidMessage> valid(Object o) {
        MstBondCreditRatingDto dto=(MstBondCreditRatingDto)o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "MstBondCreditRating002", null, ValidMessage.class);
        return resultList;
    }
}
