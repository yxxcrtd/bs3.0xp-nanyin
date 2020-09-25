/*
 * 文件名：IBusiness.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.base.entity;

import java.io.Serializable;

/**
 * 基础结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月16日
 * @see IBusiness
 * @since 1.0
 */
public interface IBusiness extends Serializable {

    /**
     * get the Redis key
     * 
     * @return Redis key
     * @see
     * @since 1.0
     */
    String getKey();

    /**
     * get the Redis hash key
     * 
     * @return Redis hash key
     * @see
     * @since 1.0
     */
    String getField();
}
