package com.joyintech.tams.disclosure.information.config.repprodinfo.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdInfoValidator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object o) {
        return null;
    }
}
