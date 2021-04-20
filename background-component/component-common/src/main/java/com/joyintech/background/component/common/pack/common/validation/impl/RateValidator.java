/*
 * 文件名：RateValidator.java
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

import org.apache.commons.lang3.math.NumberUtils;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.ValidationConstant;
import com.joyintech.background.component.common.pack.common.validation.annotation.Rate;

/**
 * 利率、收益率、费率 验证逻辑实现
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月22日
 * @see RateValidator
 * @since 1.0
 */
public class RateValidator implements ConstraintValidator<Rate, Object> {

    /**
     * 验证规则
     */
    private Pattern pattern;

    /**
     * 自定义message
     */
    private String message;
    
    /**
     * 最大值
     */
    private double maxValue;
    
    /**
     * 属性名
     */
    private String fieldName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Rate annotation) {
        this.fieldName = SpringUtil.getTextValue(annotation.fieldName());
        this.message = SpringUtil.getTextValue(annotation.message(), fieldName);
        String patternStr = StringUtil.replaceEach(ValidationConstant.PATTERN_NUMBER_DECIMAL,
            new String[] {ValidationConstant.FIELD_MAX_INT_BIT, ValidationConstant.FIELD_MAX_DEC_BIT},
            new String[] {String.valueOf(annotation.maxIntBit()), String.valueOf(annotation.maxDecBit())});
        pattern = Pattern.compile(patternStr);
        maxValue = annotation.maxValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        String msg = this.message;
        BigDecimal numberValue = NumberUtils.createBigDecimal(StringUtil.getOrElse(value, ()->null));
        Matcher matcher = pattern.matcher(StringUtil.numberToString(numberValue));
        if (matcher.matches() && (maxValue < 0 || numberValue.doubleValue() <= maxValue) && !BigDecimal.ZERO.equals(numberValue)) {
            // 小于0
            if(numberValue.doubleValue() < 0) {
                msg = SpringUtil.getTextValue("FW_E_0008", new Object[]{fieldName, 0});
            }
            else {
                return true;
            }
        }
        // 超出maxValue
        if(maxValue < Double.MAX_VALUE && numberValue.doubleValue() > maxValue) {
            msg = SpringUtil.getTextValue("FW_E_0009", new Object[]{fieldName, maxValue});
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        return false;
    }

}