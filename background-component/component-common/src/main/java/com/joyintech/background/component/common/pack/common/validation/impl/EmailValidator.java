/*
 * 文件名：EmailValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：鍒樻憾瀹?-Dosgi.requiredJavaVersion=1.8
 * 修改时间：2016年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
package com.joyintech.background.component.common.pack.common.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.ValidationConstant;
import com.joyintech.background.component.common.pack.common.validation.annotation.Email;

/**
 * 报表邮箱validator
 *
 * @author 刘溶容-Dosgi.requiredJavaVersion=1.8
 * @version 1.0 2016年11月14日
 * @see EmailValidator
 * @since 1.0
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    /**
     * 验证规则
     */
    private final Pattern pattern = Pattern.compile(ValidationConstant.PATTERN_EMAIL);

    /**
     * 自定义message
     */
    private String message;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Email constraintAnnotation) {
        String fieldName = SpringUtil.getTextValue(constraintAnnotation.fieldName());
        this.message = SpringUtil.getTextValue(constraintAnnotation.message(), fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtil.isEmpty(value)) {
            return true;
        }
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        return false;
    }

}
