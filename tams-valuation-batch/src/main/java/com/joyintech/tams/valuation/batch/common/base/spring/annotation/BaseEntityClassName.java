/*
 * 文件名：BaseEntityClassName.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2017年5月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.valuation.batch.common.base.spring.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标注基础类的类名
 * @author qiangchengxi
 * @version 1.0 2017年5月22日
 * @see BaseEntityClassName
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface BaseEntityClassName {
    Class<?> value();
}
