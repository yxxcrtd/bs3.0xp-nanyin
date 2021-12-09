package com.joyintech.tams.invest.basicdata.dayendaccount.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.dayendaccount.dto.DayEndAccount002Dto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日终清算数据核对
 * 〈功能详细描述〉
 * @author chengzhong
 * @version 1.0 2021年4月23日
 * @see DayEndAccount002Validator
 * @since 1.0
 */
@Transactional
@Component
public class DayEndAccount002Validator implements BeanValidator {


    /**
     * 增删改之前校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        DayEndAccount002Dto dto = (DayEndAccount002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        return errorList;
    }
}
