/*
 * 文件名：RemarkValidator.java
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
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.ValidationConstant;
import com.joyintech.background.component.common.pack.common.validation.annotation.Remark;

/**
 * 备注验证实现类
 * 
 * @author 何阳阳
 * @version 1.0 2016年12月21日
 * @see RemarkValidator
 * @since 1.0
 */
public class RemarkValidator implements ConstraintValidator<Remark, String> {

    /**
     * 验证规则
     */
    private final Pattern pattern = Pattern.compile(ValidationConstant.PATTERN_WORD/*ValidationConstant.PATTERN_CHINESE*/);

    /**
     * 最大长度
     */
    private int length = 200;

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
    public void initialize(Remark constraintAnnotation) {
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
        String msg = this.message;
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches() && value.length() <= length) {
            return true;
        }
        // 大于最大长度
        if(value.length() > length) {
            msg = SpringUtil.getTextValue("FW_E_0007", new Object[]{fieldName, length});
        }
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        return false;
    }
}
