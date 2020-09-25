/*
 * 文件名：DictFormat.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月13日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.disclosure.common.common.base.annotate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;


/**
 * 标记该字段需要被指定数据字典格式化
 * @author 何阳阳
 * @version 1.0 2017年12月13日
 * @see DictFormat
 * @since 1.0
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface DictFormat {
    DisclosureDictCode value();
}
