/*
 * 文件名：FeeRateEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ifeerate.entity;

/**
 * 查询费率入参
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月27日
 * @see FeeRateEntity
 * @since 1.0
 */
public class FeeRateEntity {
    /**
     * 金融产品类型_投管用：理财产品
     */
    private String prodType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 平台日
     */
    private String sysDate;

    /**
     * 截止日
     */
    private String cdate;

    /**
     * 获取 金融产品类型_投管用：理财产品
     *
     * @return 金融产品类型_投管用：理财产品
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 金融产品类型_投管用：理财产品
     *
     * @param prodType 金融产品类型_投管用：理财产品
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * 获取现金流一级类型
     * 
     * @return 现金流一级类型
     */
    public String getCashType1() {
        return cashType1;
    }

    /**
     * 设置现金流一级类型
     * 
     * @param cashType1 现金流一级类型
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    /**
     * 获取现金流二级类型
     * 
     * @return 现金流二级类型
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置获取现金流二级类型
     * 
     * @param cashType2 获取现金流二级类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    /**
     * 获取平台日
     *
     * @return 平台日
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * 设置平台日
     *
     * @param sysDate 平台日
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * 获取截止日
     *
     * @return 截止日
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置截止日
     *
     * @param cdate 截止日
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

}
