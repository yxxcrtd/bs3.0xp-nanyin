/*
 * 文件名：FixedTimeIntervalValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年12月22日
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
import com.joyintech.background.component.common.pack.common.validation.annotation.FixedTimeInterval;

/**
 * 固定时间间隔验证实现
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月22日
 * @see FixedTimeIntervalValidator
 * @since 1.0
 */
public class FixedTimeIntervalValidator implements ConstraintValidator<FixedTimeInterval, Object> {

    /**
     * 验证规则
     */
    private final Pattern pattern = Pattern.compile(ValidationConstant.PATTERN_NUMBER);

    /**
     * 最大长度
     */
    private int length;

    /**
     * 自定义message
     */
    private String message;

    /**
     * 
     */
    private String fieldName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(FixedTimeInterval constraintAnnotation) {
        fieldName = SpringUtil.getTextValue(constraintAnnotation.fieldName());
        this.message = SpringUtil.getTextValue(constraintAnnotation.message(), fieldName);
        this.length = constraintAnnotation.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object valueObj, ConstraintValidatorContext context) {
        if (!StringUtil.isNotEmpty(valueObj)) {
            return true;
        }
        String value = valueObj.toString();
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches() && value.length() <= length) {
            return true;
        }
        // 大于最大长度
        if (value.length() > length) {
            this.message = SpringUtil.getTextValue("FW_E_0007",
                new Object[] {fieldName, length});
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        return false;
    }
}