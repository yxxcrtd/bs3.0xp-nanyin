/*
 * 文件名：NotEmptyValidator.java
 * 版权：Copyright by www.joyintech.com
 * 描述：非空Validator
 * 修改人：刘二通
 * 修改时间：2016年11月7日
 * 修改内容：
 */
package com.joyintech.background.component.common.pack.common.validation.impl;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.annotation.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 非空Validator
 *
 * @author 刘二通
 * @version 2016年11月7日
 * @see NotEmptyValidator
 * @since
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

    /**
     * 自定义message
     */
    private String message;

    /**
     * 是否通过notmenpty校验
     */
//    private Page page;

    @Override
    public void initialize(NotEmpty annotation) {
//        page = CacheUtil.getPage(RequestUtil.getParam("pageIdForLog"));
        String fieldName = SpringUtil.getTextValue(annotation.fieldName());
        this.message = SpringUtil.getTextValue(annotation.message(), fieldName);
    }

    @Override
    public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
//        try {
//           Field basepath = constraintValidatorContext.getClass().getDeclaredField("basePath");
//           basepath.setAccessible(true);
//           String fieldname =basepath.get(constraintValidatorContext).toString();
//           if (page!=null){
//               Collection<ElementControl> elementControls = page.getElementcontrols();
//               if (elementControls != null && !elementControls.isEmpty()) {
//                   for (ElementControl elementControl : elementControls) {
//                       if (StringUtil.equals(elementControl.getElementField(), fieldname)) {
//                           return true;
//                       }
//                   }
//               }
//           }
//        }catch (Exception e){
//            throw new ComponentException(e);
//        }
            // 禁用默认的message的值
            constraintValidatorContext.disableDefaultConstraintViolation();
            // 重新添加错误提示语句
            constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
            return str != null && !StringUtil.EMPTY.equals(str);
    }
}
