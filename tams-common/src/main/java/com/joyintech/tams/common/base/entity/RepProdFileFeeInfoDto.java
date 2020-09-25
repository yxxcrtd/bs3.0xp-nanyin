package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

/**
 * 描述:REP_PROD_FILE_FEEINFO表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-01-14
 */
public class RepProdFileFeeInfoDto extends RepProdFileFeeInfoDtoKey {
    /**
     * 费率
     */
    private BigDecimal feerate;

    /**
     * 基数类型，本金、资产净值、实收资本等
     */
    private String basicbill;

    /**
     * 计息基础，A/360，A/365
     */
    private String basis;

    /**
     * 费率
     * @return FEERATE 费率
     */
    public BigDecimal getFeerate() {
        return feerate;
    }

    /**
     * 费率
     * @param feerate 费率
     */
    public void setFeerate(BigDecimal feerate) {
        this.feerate = feerate;
    }

    /**
     * 基数类型，本金、资产净值、实收资本等
     * @return BASICBILL 基数类型，本金、资产净值、实收资本等
     */
    public String getBasicbill() {
        return basicbill;
    }

    /**
     * 基数类型，本金、资产净值、实收资本等
     * @param basicbill 基数类型，本金、资产净值、实收资本等
     */
    public void setBasicbill(String basicbill) {
        this.basicbill = basicbill == null ? null : basicbill.trim();
    }

    /**
     * 计息基础，A/360，A/365
     * @return BASIS 计息基础，A/360，A/365
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 计息基础，A/360，A/365
     * @param basis 计息基础，A/360，A/365
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }
}