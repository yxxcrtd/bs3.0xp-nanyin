package com.joyintech.tams.report.system.chbprd.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfo001Dto;
import com.joyintech.tams.report.system.chbprd.service.ChbPrdBaseinfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChbPrdBaseinfoValidator implements BeanValidator {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;
    @Autowired
    ChbPrdBaseinfo001Service chbPrdBaseinfo001Service;

    @Override
    public List<ValidMessage> valid(Object o) {
        ChbPrdBaseinfo001Dto dto=(ChbPrdBaseinfo001Dto)o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "ChbPrdBaseinfo002", null, ValidMessage.class);
        //检验产品申报登记
        ValidMessage c = chbPrdBaseinfo001Service.checkProDeclardReg(dto);
        if(c != null) {
            resultList.add(c);
        }
        return resultList;
    }
}
