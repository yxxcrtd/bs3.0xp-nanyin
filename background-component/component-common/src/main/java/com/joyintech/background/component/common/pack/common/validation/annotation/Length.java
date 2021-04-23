/*
 * 文件名：Length.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：鍒樻憾瀹?-Dosgi.requiredJavaVersion=1.8
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

import com.joyintech.background.component.common.pack.common.validation.impl.LengthValidator;

/**
 * 报表项目长度validator
 * @author 刘溶容-Dosgi.requiredJavaVersion=1.8
 * @version 1.0 2016年11月14日
 * @see Length
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { LengthValidator.class })
public @interface Length {

    String fieldName() default "";

    int min() default 0;

    int max() default 20;

    int length() default 0;

    String message() default "FW_E_0004";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
