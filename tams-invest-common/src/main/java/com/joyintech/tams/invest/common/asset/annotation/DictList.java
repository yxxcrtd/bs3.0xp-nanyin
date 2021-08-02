/*
 * 文件名：DictList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2021年6月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.asset.annotation;

import com.joyintech.tams.constant.DictCode;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 数据字典代码查询
 * 
 * @author 裴家俊
 * @version 1.0 2021年6月30日
 * @see DictList
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface DictList {

    /**
     *
     * @return
     */
    public DictEnum match() default DictEnum.NMAE;

    /**
     * 数据字典代码
     * 
     * @return 数据字典代码
     * @see
     * @since 1.0
     */
    public DictCode link();
}
