package com.joyintech.background.component.uplink.interbank.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class IbuQuoteCrepHisKey extends BaseEntity {
    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 取得 报价编号（内部代码）
     * @return 报价编号（内部代码）
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 报价编号（内部代码）
     * @param quoteId 报价编号（内部代码）
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId == null ? null : quoteId.trim();
    }

    /**
     * 取得 版本号
     * @return 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置 版本号
     * @param version 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}