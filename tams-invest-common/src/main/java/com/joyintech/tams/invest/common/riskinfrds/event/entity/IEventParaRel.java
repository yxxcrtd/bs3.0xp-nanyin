/*
 * 文件名：IEventParaReal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.event.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 风险事件与参数的关系
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月16日
 * @see IEventParaRel
 * @since 1.0
 */
public interface IEventParaRel extends Serializable {

    /**
     * 取得 参数代码，实际存储的结构是 参数代码:参数粒度
     * 
     * @return 参数代码:参数粒度
     * @see
     * @since 1.0
     */
    String getParaCode();

    /**
     * 取得 风险事件代码
     * 
     * @return 风险事件代码
     * @see
     * @since 1.0
     */
    String getEventCode();

    /**
     * 取得 关系类型（+、-、*、/）
     * 
     * @return 关系类型（+、-、*、/）
     * @see
     * @since 1.0
     */
    String getRelType();

    /**
     * 取得 关系对象（金额、价格、数量）
     * 
     * @return 关系对象（金额、价格、数量）
     * @see
     * @since 1.0
     */
    String getRelItem();

    /**
     * 设置 关系运算的对象值
     * 
     * @param val 关系运算的对象值
     * @see
     * @since 1.0
     */
    void setRelValue(BigDecimal val);

    /**
     * 取得 关系预算的对象值
     * 
     * @return 关系预算的对象值
     * @see
     * @since 1.0
     */
    BigDecimal getRelValue();

    /**
     * 取得 自定义值
     *
     * @return 自定义值
     * @see
     * @since 1.0
     */
    BigDecimal getCustomVal();
}
