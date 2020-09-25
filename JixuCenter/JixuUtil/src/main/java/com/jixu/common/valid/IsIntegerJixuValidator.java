package com.jixu.common.valid;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为整数类型
 * @author 桃汰猪
 *
 */
public class IsIntegerJixuValidator implements ConstraintValidator<IsIntegerJixu, String> {

    private String message;

    @Override
    public void initialize(IsIntegerJixu annotation) {
        this.message = annotation.message()+annotation.keymsg();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        
        if(str==null || str.equals(""))
        	return false;
        
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str.toString()).matches();   
    }
}
