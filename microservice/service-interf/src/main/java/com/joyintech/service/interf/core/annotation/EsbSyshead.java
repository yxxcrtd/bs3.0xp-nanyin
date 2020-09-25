/*
 * 文件名：SYSHEAD.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.joyintech.service.interf.core.constant.EsbServiceInfoList;

/**
 * 系统报文头
 * 
 * @author zhangrui1
 * @version 1.0 2018年9月3日
 * @see EsbSyshead
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface EsbSyshead {

    /**
     * 接口交易信息
     * 
     * @return 接口交易信息
     * @see
     * @since 1.0
     */
    EsbServiceInfoList value();
}
