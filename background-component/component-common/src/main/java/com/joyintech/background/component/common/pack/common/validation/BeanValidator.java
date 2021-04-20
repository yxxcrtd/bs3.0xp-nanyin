/*
 * 文件名：BeanValidator.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月16日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.validation;

import java.util.List;

import com.joyintech.background.component.common.pack.common.util.SpringUtil;

/**
 * DTO验证
 *
 * @author 何阳阳
 * @version 1.0 2016年11月28日
 * @see BeanValidator
 * @since 1.0
 */
public interface BeanValidator {

    /**
     * 获取Message的i18n表示
     *
     * @param messageCode 消息代码
     * @param args 参数
     * @return message
     */
    default String getMessage(String messageCode, String... args) {
        return SpringUtil.getTextValue(messageCode, args);
    }

    /**
     * 验证方法
     *
     * @param value 需要被验证的业务对象
     * @return 验证消息，如果为空则表示验证通过
     * @since 1.0
     */
    List<ValidMessage> valid(Object value);
}
