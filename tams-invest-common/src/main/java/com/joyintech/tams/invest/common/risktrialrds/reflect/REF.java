/*
 * 文件名：REF.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年7月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.risktrialrds.reflect;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 反射注解
 * 
 * @author 裴家俊
 * @version 1.0 2020年7月6日
 * @see REF
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface REF {

    String value();
}
