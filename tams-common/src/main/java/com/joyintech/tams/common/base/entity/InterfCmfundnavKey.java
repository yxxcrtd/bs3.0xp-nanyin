package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfCmfundnavKey extends BaseEntity {
    /**
     * 基金代码
     */
    private String fInfoCode;

    /**
     * 截止日期
     */
    private String priceDate;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 基金代码
     * @return 基金代码
     */
    public String getfInfoCode() {
        return fInfoCode;
    }

    /**
     * 设置 基金代码
     * @param fInfoCode 基金代码
     */
    public void setfInfoCode(String fInfoCode) {
        this.fInfoCode = fInfoCode == null ? null : fInfoCode.trim();
    }

    /**
     * 取得 截止日期
     * @return 截止日期
     */
    public String getPriceDate() {
        return priceDate;
    }

    /**
     * 设置 截止日期
     * @param priceDate 截止日期
     */
    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate == null ? null : priceDate.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 数据来源
     * @param source 数据来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}