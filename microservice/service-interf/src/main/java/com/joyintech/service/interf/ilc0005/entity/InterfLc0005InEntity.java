/*
 * 文件名：InterfLc0005InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0005.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 理财产品报价报价查询接口Lc0005输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月8日
 * @see InterfLc0005InEntity
 * @since 1.0
 */
public class InterfLc0005InEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3152715285984822823L;

    /**
     * 产品代码
     */
    private String productcode;

    /**
     * 产品成立日
     */
    private String establishdate;

    /**
     * 产品到期日
     */
    private String expirydate;

    /**
     * 金融产品类型（投管）
     */
    private String prodType2;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 平台日期
     */
    private String cDate;

    /**
     * 金融产品代码
     */
    private String finprodid;

    /**
     * 查询现金流类型对应的生效日期
     */
    private String effdate;

    /**
     * 获取Lc0005接口.产品代码
     * 
     * @return 产品代码
     * @see
     * @since 1.0
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * 设置Lc0005接口.产品代码
     * 
     * @param productcode 产品代码
     * @see
     * @since 1.0
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * 获取Lc0005接口.产品成立日
     * 
     * @return 产品成立日
     * @see
     * @since 1.0
     */
    public String getEstablishdate() {
        return establishdate;
    }

    /**
     * 设置Lc0005接口.产品成立日
     * 
     * @param establishdate 产品成立日
     * @see
     * @since 1.0
     */
    @EsbName(value = "ESTABLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setEstablishdate(String establishdate) {
        this.establishdate = establishdate;
    }

    /**
     * 获取Lc0005接口.产品到期日
     * 
     * @return 产品到期日
     * @see
     * @since 1.0
     */
    public String getExpirydate() {
        return expirydate;
    }

    /**
     * 设置Lc0005接口.产品到期日
     * 
     * @param expirydate 产品到期日
     * @see
     * @since 1.0
     */
    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    /**
     * 获取金融产品类型（投管）
     * 
     * @return 金融产品类型（投管）
     * @see
     * @since 1.0
     */
    public String getProdType2() {
        return prodType2;
    }

    /**
     * 设置金融产品类型（投管）
     * 
     * @param prodType2 金融产品类型（投管）
     * @see
     * @since 1.0
     */
    public void setProdType2(String prodType2) {
        this.prodType2 = prodType2;
    }

    /**
     * 获取现金流一级类型
     * 
     * @return 现金流一级类型
     * @see
     * @since 1.0
     */
    public String getCashType1() {
        return cashType1;
    }

    /**
     * 设置现金流一级类型
     * 
     * @param cashType1 现金流一级类型
     * @see
     * @since 1.0
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    /**
     * 获取现金流二级类型
     * 
     * @return 现金流二级类型
     * @see
     * @since 1.0
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置现金流二级类型
     * 
     * @param cashType2 现金流二级类型
     * @see
     * @since 1.0
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    /**
     * 获取平台日期
     * 
     * @return 平台日期
     * @see
     * @since 1.0
     */
    public String getcDate() {
        return cDate;
    }

    /**
     * 设置平台日期
     * 
     * @param cDate 平台日期
     * @see
     * @since 1.0
     */
    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodid() {
        return finprodid;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodid 金融产品代码
     */
    public void setFinprodid(String finprodid) {
        this.finprodid = finprodid;
    }

    /**
     * 获取 查询现金流类型对应的生效日期
     * 
     * @return 查询现金流类型对应的生效日期
     */
    public String getEffdate() {
        return effdate;
    }

    /**
     * 设置 查询现金流类型对应的生效日期
     * 
     * @param effdate 查询现金流类型对应的生效日期
     */
    public void setEffdate(String effdate) {
        this.effdate = effdate;
    }

}
