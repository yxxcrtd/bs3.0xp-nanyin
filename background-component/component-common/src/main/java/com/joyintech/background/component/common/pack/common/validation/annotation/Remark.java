/*
 * 文件名：Remark.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年12月21日
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

import com.joyintech.background.component.common.pack.common.validation.impl.RemarkValidator;

/**
 * 备注验证注解
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月21日
 * @see Remark
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Constraint(validatedBy = RemarkValidator.class)
public @interface Remark {

    /**
     * 字段名对应的i18n编码
     */
    String fieldName();

    /**
     * 最大长度，默认值200
     */
    int length() default 200;

    String message() default "FW_E_0002";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
