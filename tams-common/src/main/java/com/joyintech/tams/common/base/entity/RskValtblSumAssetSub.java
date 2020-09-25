package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RskValtblSumAssetSub extends RskValtblSumAssetSubKey {
    /**
     * 证券数量

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal securityQuantity;

    /**
     * 单位成本

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCost;

    /**
     * 证券成本

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal securityCost;

    /**
     * 成本占净值比

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ctnwr;

    /**
     * 行情收市价

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mcp;

    /**
     * 证券市值

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal smv;

    /**
     * 市值占净值比

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mcr;

    /**
     * 估值增值

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valueAdded;

    /**
     * 停牌信息

     */
    private String suspensionInfo;

    /**
     * 权益信息

     */
    private String equityInfo;

    /**
     * 导入方式(MANUAL:手工,AUTO:自动)
     */
    private String impMode;

    /**
     * 取得 证券数量

     * @return 证券数量

     */
    public BigDecimal getSecurityQuantity() {
        return securityQuantity;
    }

    /**
     * 设置 证券数量

     * @param securityQuantity 证券数量

     */
    public void setSecurityQuantity(BigDecimal securityQuantity) {
        this.securityQuantity = securityQuantity;
    }

    /**
     * 取得 单位成本

     * @return 单位成本

     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * 设置 单位成本

     * @param unitCost 单位成本

     */
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * 取得 证券成本

     * @return 证券成本

     */
    public BigDecimal getSecurityCost() {
        return securityCost;
    }

    /**
     * 设置 证券成本

     * @param securityCost 证券成本

     */
    public void setSecurityCost(BigDecimal securityCost) {
        this.securityCost = securityCost;
    }

    /**
     * 取得 成本占净值比

     * @return 成本占净值比

     */
    public BigDecimal getCtnwr() {
        return ctnwr;
    }

    /**
     * 设置 成本占净值比

     * @param ctnwr 成本占净值比

     */
    public void setCtnwr(BigDecimal ctnwr) {
        this.ctnwr = ctnwr;
    }

    /**
     * 取得 行情收市价

     * @return 行情收市价

     */
    public BigDecimal getMcp() {
        return mcp;
    }

    /**
     * 设置 行情收市价

     * @param mcp 行情收市价

     */
    public void setMcp(BigDecimal mcp) {
        this.mcp = mcp;
    }

    /**
     * 取得 证券市值

     * @return 证券市值

     */
    public BigDecimal getSmv() {
        return smv;
    }

    /**
     * 设置 证券市值

     * @param smv 证券市值

     */
    public void setSmv(BigDecimal smv) {
        this.smv = smv;
    }

    /**
     * 取得 市值占净值比

     * @return 市值占净值比

     */
    public BigDecimal getMcr() {
        return mcr;
    }

    /**
     * 设置 市值占净值比

     * @param mcr 市值占净值比

     */
    public void setMcr(BigDecimal mcr) {
        this.mcr = mcr;
    }

    /**
     * 取得 估值增值

     * @return 估值增值

     */
    public BigDecimal getValueAdded() {
        return valueAdded;
    }

    /**
     * 设置 估值增值

     * @param valueAdded 估值增值

     */
    public void setValueAdded(BigDecimal valueAdded) {
        this.valueAdded = valueAdded;
    }

    /**
     * 取得 停牌信息

     * @return 停牌信息

     */
    public String getSuspensionInfo() {
        return suspensionInfo;
    }

    /**
     * 设置 停牌信息

     * @param suspensionInfo 停牌信息

     */
    public void setSuspensionInfo(String suspensionInfo) {
        this.suspensionInfo = suspensionInfo == null ? null : suspensionInfo.trim();
    }

    /**
     * 取得 权益信息

     * @return 权益信息

     */
    public String getEquityInfo() {
        return equityInfo;
    }

    /**
     * 设置 权益信息

     * @param equityInfo 权益信息

     */
    public void setEquityInfo(String equityInfo) {
        this.equityInfo = equityInfo == null ? null : equityInfo.trim();
    }

    /**
     * 取得 导入方式(MANUAL:手工,AUTO:自动)
     * @return 导入方式(MANUAL:手工,AUTO:自动)
     */
    public String getImpMode() {
        return impMode;
    }

    /**
     * 设置 导入方式(MANUAL:手工,AUTO:自动)
     * @param impMode 导入方式(MANUAL:手工,AUTO:自动)
     */
    public void setImpMode(String impMode) {
        this.impMode = impMode == null ? null : impMode.trim();
    }
}