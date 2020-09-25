package com.jixu.framework.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = JixuConstraintValidator.class)
public @interface JixuValidLogic {
	/**
	 * @Description: 错误提示
	 */
	String message() default "";
	
	Class<? extends JixuBeanValidator> targetClass();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
