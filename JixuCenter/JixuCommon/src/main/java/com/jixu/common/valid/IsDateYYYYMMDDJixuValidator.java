package com.jixu.common.valid;

import com.joyintech.framework.util.DateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

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
		Date pdate= DateUtil.parse(dateStr);
		if(dateStr.length()!=8 || pdate==null){
			return false;
		}else {
			
		}
		return true;
    }
}
