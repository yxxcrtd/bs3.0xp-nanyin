/*
 * 文件名：TaxBalanceQuery001Dto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：leipeng 修改时间：2018年10月9日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.taxbalancequery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 简易计税余额查询
 * 
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TaxBalanceQuery001Dto
 * @since 1.0
 */
@SuppressWarnings("serial")
public class TaxBalanceQuery001Dto extends BaseEntity {

    /** 产品代码 */
    private String finprodId;

    /** 产品名称 */
    private String finprodName;

    /** 发生日期 */
    private String happenDate;

    /** 业务类型 */
    private String taxPType;

    /** 收入类型 */
    private String vatType;

    /** 预估税费余额 */
    private BigDecimal taxAmt;

    /** 发生税费余额 */
    private BigDecimal taxAmt2;

    /** 开始日期 */
    private String startDate;

    /** 结束日期 */
    private String endDate;

    /** 资产代码 */
    private String assetId;

    /** 资产名称 */
    private String assetIdName;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetIdName() {
        return assetIdName;
    }

    public void setAssetIdName(String assetIdName) {
        this.assetIdName = assetIdName;
    }

    /**
     * 获取 taxAmt2
     * 
     * @return taxAmt2.
     */
    public BigDecimal getTaxAmt2() {
        return taxAmt2;
    }

    /**
     * 设置 taxAmt2
     * 
     * @param taxAmt2 taxAmt2
     */
    public void setTaxAmt2(BigDecimal taxAmt2) {
        this.taxAmt2 = taxAmt2;
    }

    public String getHappenDate() {
        return DateUtil.formatDateAddSplit(happenDate);
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 taxPType
     * 
     * @return taxPType.
     */
    public String getTaxPType() {
        return taxPType;
    }

    public String getTaxPTypeCode() {
        return taxPType;
    }

    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType;
    }

    /**
     * 获取 vatType
     * 
     * @return vatType.
     */
    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
