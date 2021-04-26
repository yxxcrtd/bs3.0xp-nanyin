package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class SettleTransDataAdd extends BaseEntity {
    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     */
    private String repoType;

    /**
     * 转股价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal conPrice;

    /**
     * 转股数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal conAmt;

    /**
     * 转股本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal conPrinAmt;

    /**
     * 转换资产代码/债转股代码（基金转换/债转股）
     */
    private String rFinprodId;

    /**
     * 交易所回购天数方式
     */
    private String seRepDayType;

    /**
     * 取得 成交编号
     * @return 成交编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 成交编号
     * @param tradeId 成交编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     * @return 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     */
    public String getRepoType() {
        return repoType;
    }

    /**
     * 设置 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     * @param repoType 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     */
    public void setRepoType(String repoType) {
        this.repoType = repoType == null ? null : repoType.trim();
    }

    /**
     * 取得 转股价格
     * @return 转股价格
     */
    public BigDecimal getConPrice() {
        return conPrice;
    }

    /**
     * 设置 转股价格
     * @param conPrice 转股价格
     */
    public void setConPrice(BigDecimal conPrice) {
        this.conPrice = conPrice;
    }

    /**
     * 取得 转股数量
     * @return 转股数量
     */
    public BigDecimal getConAmt() {
        return conAmt;
    }

    /**
     * 设置 转股数量
     * @param conAmt 转股数量
     */
    public void setConAmt(BigDecimal conAmt) {
        this.conAmt = conAmt;
    }

    /**
     * 取得 转股本金
     * @return 转股本金
     */
    public BigDecimal getConPrinAmt() {
        return conPrinAmt;
    }

    /**
     * 设置 转股本金
     * @param conPrinAmt 转股本金
     */
    public void setConPrinAmt(BigDecimal conPrinAmt) {
        this.conPrinAmt = conPrinAmt;
    }

    /**
     * 取得 转换资产代码/债转股代码（基金转换/债转股）
     * @return 转换资产代码/债转股代码（基金转换/债转股）
     */
    public String getrFinprodId() {
        return rFinprodId;
    }

    /**
     * 设置 转换资产代码/债转股代码（基金转换/债转股）
     * @param rFinprodId 转换资产代码/债转股代码（基金转换/债转股）
     */
    public void setrFinprodId(String rFinprodId) {
        this.rFinprodId = rFinprodId == null ? null : rFinprodId.trim();
    }

    /**
     * 取得 交易所回购天数方式
     * @return 交易所回购天数方式
     */
    public String getSeRepDayType() {
        return seRepDayType;
    }

    /**
     * 设置 交易所回购天数方式
     * @param seRepDayType 交易所回购天数方式
     */
    public void setSeRepDayType(String seRepDayType) {
        this.seRepDayType = seRepDayType == null ? null : seRepDayType.trim();
    }
}