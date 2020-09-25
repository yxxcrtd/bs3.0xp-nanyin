/*
 * 文件名：InterfG1003OutCommissionEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.entity;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 新核心记账接口G1003手续费数组Entity
 * 
 * @author changwenbin
 * @version 1.0 2018年12月3日
 * @see InterfG1003OutCommissionEntity
 * @since 1.0
 */
public class InterfG1003OutCommissionEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -7943847607159942685L;

    /**
     * 费项编号
     */
    private String feeCode;

    /**
     * 手续费币种
     */
    private String commissionCCY;

    /**
     * 手续费金额
     */
    private BigDecimal commission;

    /**
     * 税费金额
     */
    private BigDecimal taxAmt;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 获取 费项编号
     * 
     * @return 费项编号
     */
    public String getFeeCode() {
        return feeCode;
    }

    /**
     * 设置 费项编号
     * 
     * @param feeCode 费项编号
     */
    @EsbName(value = "FEE_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    /**
     * 获取 手续费币种
     * 
     * @return 手续费币种
     */
    public String getCommissionCCY() {
        return commissionCCY;
    }

    /**
     * 设置 手续费币种
     * 
     * @param commissionCCY 手续费币种
     */
    @EsbName(value = "COMMISSION_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setCommissionCCY(String commissionCCY) {
        this.commissionCCY = commissionCCY;
    }

    /**
     * 获取 手续费金额
     * 
     * @return 手续费金额
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置 手续费金额
     * 
     * @param commission 手续费金额
     */
    @EsbName(value = "COMMISSION ")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取 税费金额
     * 
     * @return 税费金额
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置 税费金额
     * 
     * @param taxAmt 税费金额
     */
    @EsbName(value = "TAX_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    /**
     * 获取 税率
     * 
     * @return 税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置 税率
     * 
     * @param taxRate 税率
     */
    @EsbName(value = "TAX_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}
