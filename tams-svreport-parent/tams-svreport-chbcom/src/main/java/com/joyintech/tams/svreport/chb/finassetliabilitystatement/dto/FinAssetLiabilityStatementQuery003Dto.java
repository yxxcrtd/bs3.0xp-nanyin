package com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto;


import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 理财产品资产负债表导出Dto
 * 
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see FinAssetLiabilityStatementQuery003Dto
 * @since 2.1.6
 */
public class FinAssetLiabilityStatementQuery003Dto extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 资产指标名称
     */
    private String assetSubjectName;

    /**
     * 资产期末余额
     */
    private BigDecimal assetEndingBalance;

    /**
     * 资产期初余额
     */
    private BigDecimal assetInitialBalance;

    /**
     * 负债所有者权益指标名称
     */
    private String liabilitiesAndOwnerEquitySubjectName;

    /**
     * 负债所有者权益期末余额
     */
    private BigDecimal liabilitiesAndOwnerEquityEndingBalance;

    /**
     * 负债所有者权益期初余额
     */
    private BigDecimal liabilitiesAndOwnerEquityInitialBalance;

    public String getAssetSubjectName() {
        return assetSubjectName;
    }

    public void setAssetSubjectName(String assetSubjectName) {
        this.assetSubjectName = assetSubjectName;
    }

    public BigDecimal getAssetEndingBalance() {
        return assetEndingBalance;
    }

    public void setAssetEndingBalance(BigDecimal assetEndingBalance) {
        this.assetEndingBalance = assetEndingBalance;
    }

    public BigDecimal getAssetInitialBalance() {
        return assetInitialBalance;
    }

    public void setAssetInitialBalance(BigDecimal assetInitialBalance) {
        this.assetInitialBalance = assetInitialBalance;
    }

    public String getLiabilitiesAndOwnerEquitySubjectName() {
        return liabilitiesAndOwnerEquitySubjectName;
    }

    public void setLiabilitiesAndOwnerEquitySubjectName(String liabilitiesAndOwnerEquitySubjectName) {
        this.liabilitiesAndOwnerEquitySubjectName = liabilitiesAndOwnerEquitySubjectName;
    }

    public BigDecimal getLiabilitiesAndOwnerEquityEndingBalance() {
        return liabilitiesAndOwnerEquityEndingBalance;
    }

    public void setLiabilitiesAndOwnerEquityEndingBalance(BigDecimal liabilitiesAndOwnerEquityEndingBalance) {
        this.liabilitiesAndOwnerEquityEndingBalance = liabilitiesAndOwnerEquityEndingBalance;
    }

    public BigDecimal getLiabilitiesAndOwnerEquityInitialBalance() {
        return liabilitiesAndOwnerEquityInitialBalance;
    }

    public void setLiabilitiesAndOwnerEquityInitialBalance(BigDecimal liabilitiesAndOwnerEquityInitialBalance) {
        this.liabilitiesAndOwnerEquityInitialBalance = liabilitiesAndOwnerEquityInitialBalance;
    }
}
