package com.jixu.framework.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jixu.common.tool.JixuContextBeansUtil;

public class JixuConstraintValidator implements ConstraintValidator<JixuValidLogic, Object> {  
	//String为校验的类型
    
	private JixuBeanValidator validator;
	
	@Override
    public void initialize(JixuValidLogic myConstraint) {
		validator=JixuContextBeansUtil.getBean(myConstraint.targetClass());
	}
 
    /**
    * @Description: 自定义校验逻辑
    */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	 List<JixuValidMessage> validResult = validator.valid(value);
         if (null == validResult || validResult.isEmpty()) {
             return true;
         }
         else {
            
             context.disableDefaultConstraintViolation();
             for (JixuValidMessage message : validResult) {
                 context.buildConstraintViolationWithTemplate(message.getMessage()).addPropertyNode(
                     message.getField()).addConstraintViolation();
             }
         }
         return false;
    }
}
