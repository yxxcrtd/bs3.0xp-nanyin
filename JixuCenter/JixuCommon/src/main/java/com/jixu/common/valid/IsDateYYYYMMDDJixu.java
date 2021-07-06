package com.jixu.common.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * 检验是否为数字类型
 * @author 桃汰猪
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { IsDateYYYYMMDDJixuValidator.class })
public @interface IsDateYYYYMMDDJixu {
	String keymsg();

    String message() default "请输入正确的日期类型(YYYY-MM-DD):";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
