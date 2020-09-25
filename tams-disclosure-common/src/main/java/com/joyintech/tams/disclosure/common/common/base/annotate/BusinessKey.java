/*
 * 文件名：BusinessKey.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月12日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.disclosure.common.common.base.annotate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 该注解用来标记业务主键
 * @author 何阳阳
 * @version 1.0 2017年12月12日
 * @see BusinessKey
 * @since 1.0
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface BusinessKey {
    String value();
}
