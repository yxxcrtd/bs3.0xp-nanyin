/*
 * 文件名：LengthValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：李国瑞
 * 修改时间：2016年12月9日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.background.component.common.pack.common.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.Length;

/**
 * 画面LengthValidatorClass
 *
 * @author 李国瑞
 * @version 1.0 2016年12月9日
 * @see LengthValidator
 * @since 1.0
 */
public class LengthValidator implements ConstraintValidator<Length, Object> {

    /**
     * 自定义message
     */
    private String message;

    /**
     * 最小值
     */
    private int min;

    /**
     * 最大值
     */
    private int max;

    /**
     * 长度
     */
    private int length;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Length annotation) {
        String fieldName = SpringUtil.getTextValue(annotation.fieldName());
        int max = annotation.max();
        int min = annotation.min();
        int length = annotation.length();
        if (min > 0) {
            this.min = min;
        }
        if (max > 0) {
            this.max = max;
        }
        // add by zhangrui1 start
        if (length > 0) {
            this.length = length;
        }
        // add by zhangrui1 end
        if (length == 0) {
            this.message = SpringUtil.getTextValue("FW_E_0007", fieldName,
                String.valueOf(max));
        }
        else {
            this.message = SpringUtil.getTextValue("FW_E_0004", fieldName,
                String.valueOf(length));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 禁用默认的message的值
        constraintValidatorContext.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        constraintValidatorContext.buildConstraintViolationWithTemplate(
            this.message).addConstraintViolation();
        if (value == null) {
            return true;
        }
        int strLength = value.toString().length();
        if (this.length > 0) {
            return strLength == this.length;
        }
        return strLength >= min && strLength <= max;
    }

}
