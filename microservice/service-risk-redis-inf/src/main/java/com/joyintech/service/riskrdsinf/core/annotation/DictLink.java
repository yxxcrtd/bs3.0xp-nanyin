/*
 * 文件名：DictLink.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.joyintech.service.riskrdsinf.core.constant.InfDictCode;

/**
 * 数据字典转换绑定
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月18日
 * @see DictLink
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface DictLink {

    /**
     * 关联数据字典代码
     * 
     * @return 关联数据字典代码
     * @see
     * @since 1.0
     */
    public InfDictCode link();
}
