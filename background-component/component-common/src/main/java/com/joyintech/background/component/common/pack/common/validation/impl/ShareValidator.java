/*
 * 文件名：ShareValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年12月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.background.component.common.pack.common.validation.impl;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.ValidationConstant;
import com.joyintech.background.component.common.pack.common.validation.annotation.Share;

/**
 * 份额验证逻辑实现
 * @author 何阳阳
 * @version 1.0 2016年12月22日
 * @see ShareValidator
 * @since 1.0
 */
public class ShareValidator implements ConstraintValidator<Share, BigDecimal> {

    /**
     * 验证规则
     */
    private Pattern pattern;

    /**
     * 自定义message
     */
    private String message;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Share annotation) {
        String fieldName = SpringUtil.getTextValue(annotation.fieldName());
        this.message = SpringUtil.getTextValue(annotation.message(), fieldName);
        String patternStr = StringUtil.replaceEach(ValidationConstant.PATTERN_NUMBER_DECIMAL,
            new String[] {ValidationConstant.FIELD_MAX_INT_BIT, ValidationConstant.FIELD_MAX_DEC_BIT},
            new String[] {String.valueOf(annotation.maxIntBit()), String.valueOf(annotation.maxDecBit())});
        pattern = Pattern.compile(patternStr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        Matcher matcher = pattern.matcher(value.toString());
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
