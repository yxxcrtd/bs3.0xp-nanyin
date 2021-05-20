package com.joyintech.tams.report.system.bonddiscountrate.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChbSecDiscountRateValidator implements BeanValidator {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @Override
    public List<ValidMessage> valid(Object o) {
        ChbSecDiscountRateDto dto=(ChbSecDiscountRateDto)o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "ChbSecDiscountRate002", null, ValidMessage.class);
        if (dto.getDiscountRate().compareTo(new BigDecimal(100)) == 1 || new BigDecimal(0).compareTo(dto.getDiscountRate()) == 1){
            resultList.add(new ValidMessage("折扣率（%）必须为0-100之内的数。"));
        }
        return resultList;
    }
}
