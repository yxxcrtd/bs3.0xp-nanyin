package com.joyintech.tams.svreport.chb.boksubject.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wubingbing
 * @date 2022-03-28 14:42
 */
@Component
public class BokSubjectDtoValidator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object o) {
        return null;
    }
}
