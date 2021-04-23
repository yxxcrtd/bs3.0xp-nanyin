/*
 * 文件名：PatternValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：鍒樻憾瀹?-Dosgi.requiredJavaVersion=1.8
 * 修改时间：2016年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
package com.joyintech.background.component.common.pack.common.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.Pattern;

/**
 * 报表项目正则validator
 *
 * @author 刘溶容-Dosgi.requiredJavaVersion=1.8
 * @version 1.0 2016年11月14日
 * @see PatternValidator
 * @since 1.0
 */
public class PatternValidator implements ConstraintValidator<Pattern, Object> {

    /**
     *
     */
    private String message;

    /**
     */
    private java.util.regex.Pattern pattern;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Pattern constraintAnnotation) {
        String fieldName = SpringUtil.getTextValue(constraintAnnotation.fieldName());
        this.message = SpringUtil.getTextValue(constraintAnnotation.message(), fieldName);
        Pattern.Flag[] flags = constraintAnnotation.flags();
        int intFlag = 0;
        for (Pattern.Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }
        try {
            pattern = java.util.regex.Pattern.compile(constraintAnnotation.regx(), intFlag);
        }
        catch (PatternSyntaxException e) {
            throw e;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!StringUtil.isNotEmpty(value)) {
            return true;
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        Matcher m = pattern.matcher(value.toString());
        return m.matches();
    }
}
