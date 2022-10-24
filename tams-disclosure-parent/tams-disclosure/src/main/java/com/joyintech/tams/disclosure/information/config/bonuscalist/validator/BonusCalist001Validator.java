package com.joyintech.tams.disclosure.information.config.bonuscalist.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dto.BonusCalist001Dto;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Component
public class BonusCalist001Validator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object value) {
        BonusCalist001Dto dto = (BonusCalist001Dto) value;
        List<ValidMessage> resultList = new ArrayList<>();
        if (dto.getOperateType()!="delete"){
            if (DateUtil.compareDate(dto.getProfitBaseDate(),dto.getLastProfitBaseDate())<= 0){
                resultList.add(new ValidMessage("上一收益分配基准日应小于本期收益分配基准日！"));
            }
        }

        return resultList;
    }
}
