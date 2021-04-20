/*
 * 文件名：CustomRule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
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

import com.joyintech.background.component.common.pack.common.validation.impl.CustomRuleValidator;

/**
 * 自定义规则验证器
 * 
 * @author 何阳阳
 * @version 1.0 2018年5月17日
 * @see CustomRule
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Constraint(validatedBy = { CustomRuleValidator.class })
public @interface CustomRule {
    /**
     * 字段名对应的i18n编码
     */
    String fieldName();

    /**
     * 自定义规则对应系统参数表中的KEY
     */
    String ruleKey();

    String message() default "FW_E_0002";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
