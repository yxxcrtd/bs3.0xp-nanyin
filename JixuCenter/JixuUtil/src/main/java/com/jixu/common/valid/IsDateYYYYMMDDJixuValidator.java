package com.jixu.common.valid;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jixu.common.util.DateUtil;

/**
 * 校验是否为整数类型
 * @author 桃汰猪
 *
 */
public class IsDateYYYYMMDDJixuValidator implements ConstraintValidator<IsDateYYYYMMDDJixu, String> {

    private String message;

    @Override
    public void initialize(IsDateYYYYMMDDJixu annotation) {
        this.message = annotation.message()+annotation.keymsg();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        
        if(str==null || str.equals(""))
        	return false;
        
        String dateStr=str.replaceAll("-", "");
		dateStr=dateStr.replaceAll("/", "");
		Date pdate=DateUtil.convertStr2Date(dateStr);
		if(dateStr.length()!=8 || pdate==null){
			return false;
		}else {
			
		}
		return true;
    }
}
