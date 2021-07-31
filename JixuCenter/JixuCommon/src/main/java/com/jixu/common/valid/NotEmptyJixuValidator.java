package com.jixu.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyJixuValidator implements ConstraintValidator<NotEmptyJixu, Object> {

    private String message;

    @Override
    public void initialize(NotEmptyJixu annotation) {
        this.message = annotation.message()+annotation.keymsg();
    }

    @Override
    public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();

        return str != null && !str.equals("");
    }
}
