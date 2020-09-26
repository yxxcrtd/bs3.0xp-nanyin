/*
 * 文件名：TableName.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月11日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.common.base.annotate;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对应的表名
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月11日
 * @see TableName
 * @since 1.0
 */
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {

    String value();
}
