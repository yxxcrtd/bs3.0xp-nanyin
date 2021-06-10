package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbInvestTradeErrordata extends BaseEntity {
    /**
     * uuid主键
     */
    private String datauuid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 行内交易编码
     */
    private String tradeCode;

    /**
     * 取得 uuid主键
     * @return uuid主键
     */
    public String getDatauuid() {
        return datauuid;
    }

    /**
     * 设置 uuid主键
     * @param datauuid uuid主键
     */
    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid == null ? null : datauuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 行内交易编码
     * @return 行内交易编码
     */
    public String getTradeCode() {
        return tradeCode;
    }

    /**
     * 设置 行内交易编码
     * @param tradeCode 行内交易编码
     */
    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }
}