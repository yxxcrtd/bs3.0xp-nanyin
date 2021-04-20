package com.joyintech.background.component.uplink.interbank.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class WflIbuQuoteCrepPledgeKey extends BaseEntity {
    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

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