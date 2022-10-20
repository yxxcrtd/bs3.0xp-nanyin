package com.joyintech.tams.disclosure.information.config.bonuscalist.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BonusCalist001Validator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object value) {
        return null;
    }
}
