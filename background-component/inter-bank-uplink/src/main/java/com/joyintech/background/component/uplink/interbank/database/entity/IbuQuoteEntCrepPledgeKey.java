package com.joyintech.background.component.uplink.interbank.database.entity;


import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class IbuQuoteEntCrepPledgeKey extends BaseEntity {
    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodId;

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

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 质押金融产品代码
     * @return 质押金融产品代码
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    /**
     * 设置 质押金融产品代码
     * @param pledgeFinprodId 质押金融产品代码
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId == null ? null : pledgeFinprodId.trim();
    }
}