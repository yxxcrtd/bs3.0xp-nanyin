/*
 * 文件名：ValidLogic.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月16日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */
 
package com.joyintech.background.component.common.pack.common.validation.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.joyintech.background.component.common.pack.common.validation.BeanValidator;
import com.joyintech.background.component.common.pack.common.validation.impl.ValidLogicImpl;

/**
 * 业务逻辑验证
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see ValidLogic
 * @since 1.0
 */

@Constraint(validatedBy = ValidLogicImpl.class)
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ValidLogic {
    
    String message() default "";

    Class<? extends BeanValidator> targetClass();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
