package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RepAssetsComb extends RepAssetsCombKey {
    /**
     * 资产名称
     */
    private String assetsName;

    /**
     * 公允价值（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fairValue;

    /**
     * 占产品资产比例（%）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pdProp;

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetsName() {
        return assetsName;
    }

    /**
     * 设置 资产名称
     * @param assetsName 资产名称
     */
    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName == null ? null : assetsName.trim();
    }

    /**
     * 取得 公允价值（元）
     * @return 公允价值（元）
     */
    public BigDecimal getFairValue() {
        return fairValue;
    }

    /**
     * 设置 公允价值（元）
     * @param fairValue 公允价值（元）
     */
    public void setFairValue(BigDecimal fairValue) {
        this.fairValue = fairValue;
    }

    /**
     * 取得 占产品资产比例（%）
     * @return 占产品资产比例（%）
     */
    public BigDecimal getPdProp() {
        return pdProp;
    }

    /**
     * 设置 占产品资产比例（%）
     * @param pdProp 占产品资产比例（%）
     */
    public void setPdProp(BigDecimal pdProp) {
        this.pdProp = pdProp;
    }
}