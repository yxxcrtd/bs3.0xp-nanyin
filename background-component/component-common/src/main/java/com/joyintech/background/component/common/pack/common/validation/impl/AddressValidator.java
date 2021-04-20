/*
 * 文件名：AddressValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年12月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.background.component.common.pack.common.validation.impl;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.ValidationConstant;
import com.joyintech.background.component.common.pack.common.validation.annotation.Address;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 地址验证注解实现
 * @author 何阳阳
 * @version 1.0 2016年12月21日
 * @see AddressValidator
 * @since 1.0
 */
public class AddressValidator implements ConstraintValidator<Address, String> {

    /**
     * 验证规则
     */
    private final Pattern pattern = Pattern.compile(ValidationConstant.PATTERN_ADDRESS);

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
    public void initialize(Address constraintAnnotation) {
        fieldName = SpringUtil.getTextValue(constraintAnnotation.fieldName());
        this.message = SpringUtil.getTextValue(constraintAnnotation.message(), fieldName);
        this.length = constraintAnnotation.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtil.isEmpty(value)) {
            return true;
        }
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches() && value.length() <= length) {
            return true;
        }
        // 大于最大长度
        if(value.length() > length) {
            this.message = SpringUtil.getTextValue("FW_E_0007", new Object[]{fieldName, length});
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        return false;
    }

}
