/*
 * 文件名：YiledEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.yield.entity;

import java.math.BigDecimal;

/**
 * 收益率信息
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月14日
 * @see YiledEntity
 * @since 1.0
 */
public class YiledEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 估值日
     */
    private String valDate;

    /**
     * 前一个日期的累计净值
     */
    private BigDecimal vTotalNetVal;

    /**
     * 获取 估值日
     * 
     * @return 估值日
     */
    public String getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日
     * 
     * @param valDate 估值日
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    /**
     * 获取前一个日期的累计净值
     * 
     * @return 前一个日期的累计净值
     */
    public BigDecimal getvTotalNetVal() {
        return vTotalNetVal;
    }

    /**
     * 设置 前一个日期的累计净值
     * 
     * @param vTotalNetVal 前一个日期的累计净值
     */
    public void setvTotalNetVal(BigDecimal vTotalNetVal) {
        this.vTotalNetVal = vTotalNetVal;
    }

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

}
