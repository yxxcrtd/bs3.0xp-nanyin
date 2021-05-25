package com.joyintech.tams.report.system.chbmst.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChbMstCounterPartyValidator implements BeanValidator {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @Override
    public List<ValidMessage> valid(Object o) {
        ChbMstCounterPartyDto dto=(ChbMstCounterPartyDto)o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "ChbMstCounterParty002", null, ValidMessage.class);
        return resultList;
    }
}
