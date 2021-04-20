/*
 * 文件名：AmountValidator.java
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
import com.joyintech.background.component.common.pack.common.validation.annotation.Amount;

/**
 * 金额验证实现类
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月22日
 * @see AmountValidator
 * @since 1.0
 */
public class AmountValidator implements ConstraintValidator<Amount, BigDecimal> {

    /**
     * 验证规则
     */
    private Pattern pattern = null;

    /**
     * 自定义message
     */
    private String message;

    /**
     * 属性名
     */
    private String fieldName;

    /**
     * 是否可以为负数
     */
    private boolean canMinus;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Amount annotation) {
        String fieldName = SpringUtil.getTextValue(annotation.fieldName());
        this.fieldName = fieldName;
        this.message = SpringUtil.getTextValue(annotation.message(), fieldName);
        String patternStr = StringUtil.replaceEach(ValidationConstant.PATTERN_NUMBER_DECIMAL,
            new String[] {ValidationConstant.FIELD_MAX_INT_BIT, ValidationConstant.FIELD_MAX_DEC_BIT},
            new String[] {String.valueOf(annotation.maxIntBit()), String.valueOf(annotation.maxDecBit())});
        canMinus = annotation.canMinus();
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
        String msg = this.message;
        Matcher matcher = pattern.matcher(value.toPlainString());
        if (matcher.matches()) {
            if(!canMinus && value.doubleValue() < 0) {
                msg = SpringUtil.getTextValue("FW_E_0008", new Object[]{fieldName, 0});
            }
            else {
                return true;
            }
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        return false;
    }

}
