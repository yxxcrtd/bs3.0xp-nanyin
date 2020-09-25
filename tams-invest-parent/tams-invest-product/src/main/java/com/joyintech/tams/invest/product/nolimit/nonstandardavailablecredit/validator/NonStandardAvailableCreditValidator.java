package com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.dto.NonStandardAvailableCredit001Dto;
/**
 * 校验类
 * 
 */
@Component
public class NonStandardAvailableCreditValidator implements BeanValidator{
    
    private  static final int NUMBER_MAX_LENGTH = 30;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
         List<ValidMessage> validList = new ArrayList<>();
         NonStandardAvailableCredit001Dto nonStandardAvailableCredit001Dto = (NonStandardAvailableCredit001Dto)value;
          return validList;
    }
    
    
}
