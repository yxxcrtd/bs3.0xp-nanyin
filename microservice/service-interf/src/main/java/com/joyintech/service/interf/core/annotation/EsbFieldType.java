/*
 * 文件名：FieldTypeS.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 接口字段类型 </br>
 * 对应接口类型： </br>
 * &nbsp;&nbsp;&nbsp;&nbsp;FieldTypeS.STRING <-> FieldType.FIELD_STRING </br>
 * &nbsp;&nbsp;&nbsp;&nbsp;FieldTypeS.DOUBLE <-> FieldType.FIELD_DOUBLE </br>
 * &nbsp;&nbsp;&nbsp;&nbsp;FieldTypeS.INT <-> FieldType.FIELD_INT
 * 
 * @author zhangrui1
 * @version 1.0 2018年9月26日
 * @see EsbFieldType
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface EsbFieldType {

    /**
     * 字符串类型
     */
    String STRING = "STRING";

    /**
     * 金额类型
     */
    String DOUBLE = "DOUBLE";

    /**
     * 数字类型
     */
    String INT = "INT";

    /**
     * 数组类型
     */
    String ARRAY = "ARRAY";

    /**
     * 字段类型
     */
    String value();
}
