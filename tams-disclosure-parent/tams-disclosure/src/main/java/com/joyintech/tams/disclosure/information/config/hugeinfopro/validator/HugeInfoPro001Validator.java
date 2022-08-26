package com.joyintech.tams.disclosure.information.config.hugeinfopro.validator;


import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品巨额赎回信息校验
 * @author zhoubin
 */
@Component
public class HugeInfoPro001Validator implements BeanValidator {



    @Override
    public List<ValidMessage> valid(Object value) {
        HugeInfoPro001Dto dto = (HugeInfoPro001Dto) value;
        String triggerRemark = dto.getTriggerRemark();
        String rejectRemark = dto.getRejectRemark();
        List<ValidMessage> errorList = new ArrayList<>();
        //触发的情形说明和拒绝赎回说明是否超长
        if (triggerRemark!=null && triggerRemark.length() > 500) {
            errorList.add(new ValidMessage("triggerRemark", "触发的情形说明文字不能查过500字符"));
        }
        if (rejectRemark!=null && rejectRemark.length() > 500) {
            errorList.add(new ValidMessage("rejectRemark", "拒绝赎回说明不能查过500字符"));
        }

        return errorList;
    }

}
