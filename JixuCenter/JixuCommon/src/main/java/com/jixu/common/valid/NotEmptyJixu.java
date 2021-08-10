package com.jixu.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { NotEmptyJixuValidator.class })
public @interface NotEmptyJixu {

    String keymsg();

    String message() default "请输入";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
