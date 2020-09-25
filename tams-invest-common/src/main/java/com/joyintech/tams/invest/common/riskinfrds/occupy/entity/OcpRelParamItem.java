/*
 * 文件名：OcpRelParamItem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 预占/实占相关参数存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月27日
 * @see OcpRelParamItem
 * @since 1.0
 */
public class OcpRelParamItem implements IOcpRelParamItem {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8791102188337739563L;

    /**
     * 指标参数编号
     */
    private String paramCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 预占/实占操作符号
     */
    private String occupyOper;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOccupyOper() {
        return occupyOper;
    }

    /**
     * 预占/实占的对象
     */
    private String occupyItem;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOccupyItem() {
        return occupyItem;
    }

    /**
     * 预占/实占金额
     */
    private BigDecimal occupyVal;

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getOccupyVal() {
        return occupyVal;
    }

    /**
     * 设置 指标参数编号
     * 
     * @param paramCode 指标参数编号
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    /**
     * 设置 预占/实占操作符号
     * 
     * @param occupyOper 预占/实占操作符号
     */
    public void setOccupyOper(String occupyOper) {
        this.occupyOper = occupyOper;
    }

    /**
     * 设置 预占/实占的对象
     * 
     * @param occupyItem 预占/实占的对象
     */
    public void setOccupyItem(String occupyItem) {
        this.occupyItem = occupyItem;
    }

    /**
     * 设置 预占/实占金额
     * 
     * @param occupyVal 预占/实占金额
     */
    public void setOccupyVal(BigDecimal occupyVal) {
        this.occupyVal = occupyVal;
    }

    /**
     * 参数值Redis存储Field
     */
    private Set<String> redisFields;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getRedisFields() {
        return redisFields;
    }

    /**
     * 设置 参数值Redis存储Field
     * 
     * @param redisFields 参数值Redis存储Field
     */
    public void setRedisFields(Set<String> redisFields) {
        this.redisFields = redisFields;
    }
}
