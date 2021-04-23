/*
 * 文件名：Email.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.background.component.common.pack.common.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.joyintech.background.component.common.pack.common.validation.impl.EmailValidator;

/**
 * 邮箱validator
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月14日
 * @see Email
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = {EmailValidator.class})
public @interface Email {

    String message() default "FW_E_0002";

    Class<?>[] groups() default {};

    String fieldName();

    Class<? extends Payload>[] payload() default {};
}
