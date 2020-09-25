/*
 * 文件名：IOcpRelParamItem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * 预占/实占参数信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月27日
 * @see IOcpRelParamItem
 * @since 1.0
 */
public interface IOcpRelParamItem extends Serializable {

    /**
     * 取得 指标参数代码
     * 
     * @return 指标参数代码
     * @see
     * @since 1.0
     */
    String getParamCode();

    /**
     * 取得 参数值Redis存储Field
     * 
     * @return 参数值Redis存储Field
     * @see
     * @since 1.0
     */
    Set<String> getRedisFields();

    /**
     * 取得 预占/实占操作符号
     * 
     * @return 预占/实占操作符号
     * @see
     * @since 1.0
     */
    String getOccupyOper();

    /**
     * 取得 预占/实占的对象（金额、价格、数量）
     * 
     * @return 预占/实占的对象
     * @see
     * @since 1.0
     */
    String getOccupyItem();

    /**
     * 取得 预占/实占金额
     * 
     * @return 预占/实占金额
     * @see
     * @since 1.0
     */
    BigDecimal getOccupyVal();

    /**
     * 设置 预占/实占金额
     * 
     * @param occupyVal 预占/实占金额
     * @see
     * @since 1.0
     */
    void setOccupyVal(BigDecimal occupyVal);
}
