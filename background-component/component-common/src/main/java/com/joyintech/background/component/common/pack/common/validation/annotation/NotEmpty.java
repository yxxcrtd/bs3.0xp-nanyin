/*
 * 文件名：NotEmpty.java
 * 版权：Copyright by www.joyintech.com
 * 描述：非空Validator
 * 修改人：刘二通
 * 修改时间：2016年11月7日
 * 修改内容：
 */

package com.joyintech.background.component.common.pack.common.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.joyintech.background.component.common.pack.common.validation.impl.NotEmptyValidator;

/**
 * 非空Validator
 *
 * @author 刘二通
 * @version 2016年11月7日
 * @see NotEmpty
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

    String fieldName();

    String message() default "FW_E_0006";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}