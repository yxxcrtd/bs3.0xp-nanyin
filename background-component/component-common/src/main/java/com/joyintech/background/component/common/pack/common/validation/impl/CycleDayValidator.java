/*
 * 文件名：CycleDayValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年12月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.background.component.common.pack.common.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.validation.ValidationConstant;
import com.joyintech.background.component.common.pack.common.validation.annotation.CycleDay;

/**
 * 周期（天）验证实现类
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月21日
 * @see CycleDayValidator
 * @since 1.0
 */
public class CycleDayValidator implements ConstraintValidator<CycleDay, Integer> {

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
    public void initialize(CycleDay constraintAnnotation) {
        fieldName = SpringUtil.getTextValue(constraintAnnotation.fieldName());
        this.message = SpringUtil.getTextValue(constraintAnnotation.message(), fieldName);
        this.length = constraintAnnotation.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String valueStr = value.toString();
        Matcher matcher = pattern.matcher(valueStr);
        if (matcher.matches() && valueStr.length() <= length) {
            return true;
        }
        // 大于最大长度
        if (valueStr.length() > length) {
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
