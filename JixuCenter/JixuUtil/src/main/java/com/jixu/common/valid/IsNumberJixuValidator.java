package com.jixu.common.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为数字类型
 * @author 桃汰猪
 *
 */
public class IsNumberJixuValidator implements ConstraintValidator<IsNumberJixu, String> {

    private String message;

    @Override
    public void initialize(IsNumberJixu annotation) {
        this.message = annotation.message()+annotation.keymsg();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        
        if(str==null || str.equals(""))
        	return false;
        
        Pattern pattern = Pattern.compile("-?[0-9]*.?[0-9]*");
	    Matcher matcher = pattern.matcher((CharSequence) str);
	    return matcher.matches();
    }
}
