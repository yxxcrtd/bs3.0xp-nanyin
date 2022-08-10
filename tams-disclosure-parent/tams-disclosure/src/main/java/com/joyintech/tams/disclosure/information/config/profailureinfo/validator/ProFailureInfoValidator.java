package com.joyintech.tams.disclosure.information.config.profailureinfo.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.service.ProFailureInfo001Service;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ProFailureInfoValidator implements BeanValidator {

    @Autowired
    private ProFailureInfo001Service proFailureInfo001Service;
    @Override
    public List<ValidMessage> valid(Object o) {
        ProFailureInfo001Dto dto = (ProFailureInfo001Dto)o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(),"new")){
            int count = proFailureInfo001Service.hasRepeatedData(dto);
            if (count > 0 && StringUtil.equals(dto.getStatus(),"E")){
                errorList.add(new ValidMessage("","产品登记编码，不成立原因，报告发布日期不能重复！"));
            }
        }
        return errorList;
    }
}
