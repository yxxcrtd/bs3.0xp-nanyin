/*
 * 文件名：CustomRuleValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.validation.impl;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.CustomRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义规则验证器实现类
 * 
 * @author 何阳阳
 * @version 1.0 2018年5月17日
 * @see CustomRuleValidator
 * @since 1.0
 */
public class CustomRuleValidator implements ConstraintValidator<CustomRule, String> {
//
//    /**
//     * 日志
//     */
//    private final Logger logger = LoggerFactory.getLogger(CustomRuleValidator.class);

    /**
     * 验证规则
     */
    private String ruleKey;

    /**
     * 自定义message
     */
    private String message;

    /**
     * 
     */
    private String fieldName;

    @Override
    public void initialize(CustomRule constraintAnnotation) {
        ruleKey = constraintAnnotation.ruleKey();
        fieldName = SpringUtil.getTextValue(constraintAnnotation.fieldName());
        message = SpringUtil.getTextValue(constraintAnnotation.message(), fieldName);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtil.isEmpty(value) || StringUtil.isEmpty(ruleKey)) {
            return true;
        }
//        String rulePattern = CacheUtil.getSysParam(ruleKey);
//        if(StringUtil.isEmpty(rulePattern)) {
//            if(logger.isDebugEnabled()) {
//                logger.debug("未配置验证规则{}", ruleKey);
//            }
//            return true;
//        }
        Pattern pattern = Pattern.compile(ruleKey);
        Matcher matcher = pattern.matcher(value);
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
