/*
 * 文件名：ValidLogicImpl.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月5日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.validation.impl;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.validation.BeanValidator;
import com.joyintech.background.component.common.pack.common.validation.ValidMessage;
import com.joyintech.background.component.common.pack.common.validation.annotation.ValidLogic;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * DTO验证
 *
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see ValidLogicImpl
 * @since 1.0
 */
public class ValidLogicImpl implements ConstraintValidator<ValidLogic, Object> {

    /**
     * BeanValidator
     */
    private BeanValidator validator;

    /**
     * {@inheritDoc}
     */
    @Override public void initialize(ValidLogic constraintAnnotation) {
        //      validator = constraintAnnotation.targetClass().newInstance();
        validator = SpringUtil.getBean(constraintAnnotation.targetClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isValid(Object value, ConstraintValidatorContext context) {
        List<ValidMessage> validResult = validator.valid(value);
        //根据数据库配置进行必填校验
        // 禁用默认的message的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        for (ValidMessage message : validResult) {
            context.buildConstraintViolationWithTemplate(message.getMessage()).addPropertyNode(
                    message.getField()).addConstraintViolation();
        }
        return false;
    }
}
