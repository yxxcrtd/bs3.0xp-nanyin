/*
 * 文件名：IsIDCard.java
 * 版权：Copyright by www.joyintech.com
 * 描述：
 * 修改人：何阳阳
 * 修改时间：2016年10月9日
 * 修改内容：
 */

package com.joyintech.background.component.common.pack.common.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.joyintech.background.component.common.pack.common.validation.impl.IDCardValidator;

/**
 * 身份证号码校验注解
 * 
 * @author 何阳阳
 * @version 2016年10月9日
 * @see IDCard
 * @since
 */
@Constraint(validatedBy = IDCardValidator.class) // 具体的实现
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IDCard {

    String fieldName();

    String message() default "FW_E_0002"; // 提示信息,可以写死,可以填写国际化的key

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
