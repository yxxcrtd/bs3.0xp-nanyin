/*
 * 文件名：Number.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V3.0
 * 修改人：裴家俊
 * 修改时间：2021年7月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V3.0
 */

package com.joyintech.tams.invest.common.asset.annotation;

import com.joyintech.tams.constant.DictCodeListExt1;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 解析文件时对应的字段位置
 * 
 * @author 裴家俊
 * @version 1.0 2021年7月20日
 * @see Number
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface Number {
    int value();
    DictCodeListExt1 unit() default DictCodeListExt1.单位_元;
}
