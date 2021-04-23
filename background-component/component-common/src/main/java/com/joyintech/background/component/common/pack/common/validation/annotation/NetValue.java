/*
 * 文件名：NetValue.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年12月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
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

import com.joyintech.background.component.common.pack.common.validation.impl.NetValueValidator;

/**
 * 净值验证注解
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月22日
 * @see NetValue
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Constraint(validatedBy = NetValueValidator.class)
public @interface NetValue {

    /**
     * 字段名对应的i18n编码
     */
    String fieldName();

    /**
     * 最大整数位数，默认为20
     */
    int maxIntBit() default 20;

    /**
     * 最大小数位数，默认为6
     */
    int maxDecBit() default 6;

    String message() default "FW_E_0002";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
