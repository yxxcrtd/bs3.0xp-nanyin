/*
 * 文件名：Index.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.invest.interf.common.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 解析文件时对应的字段位置
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Index
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface Index {

    int value();
}
