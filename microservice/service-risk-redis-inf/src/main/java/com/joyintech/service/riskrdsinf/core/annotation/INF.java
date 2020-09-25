/*
 * 文件名：INF.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 接口信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月26日
 * @see INF
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface INF {

    String value();
}
