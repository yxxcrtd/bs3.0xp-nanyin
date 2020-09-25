/*
 * 文件名：ParamMapEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.entity;

/**
 * 查询参数列表
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see ParamMapEntity
 * @since 1.0
 */
public class ParamMapEntity {

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 金融产品类型
     */
    private String prodType;

    /**
     * 生效日
     */
    private String cdate;

    /**
     * 处理模式
     */
    private String typeList;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码.
     */
    public String getFinProdId() {
        return finProdId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finProdId 金融产品代码
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 金融产品类型
     * 
     * @return 金融产品类型.
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 金融产品类型
     * 
     * @param prodType 金融产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * 获取 生效日
     * 
     * @return 生效日.
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 生效日
     * 
     * @param cdate 生效日
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 处理模式
     * 
     * @return 处理模式.
     */
    public String getTypeList() {
        return typeList;
    }

    /**
     * 设置 处理模式
     * 
     * @param typeList 处理模式
     */
    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    /**
     * 获取 是否分层
     * 
     * @return 是否分层.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 是否分层
     * 
     * @param isLay 是否分层
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 现金流类型
     * 
     * @return 现金流类型.
     */
    public String getCashType() {
        return cashType;
    }

    /**
     * 设置 现金流类型
     * 
     * @param cashType 现金流类型
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }
}
