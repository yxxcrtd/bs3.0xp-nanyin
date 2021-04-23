package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class IbuPortfolioAcctRelaKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 交易成员ID，21位编号
     */
    private String partyId;

    /**
     * 银行间账户类型，自营/理财
     */
    private String cfetsAcctType;

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 交易成员ID，21位编号
     * @return 交易成员ID，21位编号
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * 设置 交易成员ID，21位编号
     * @param partyId 交易成员ID，21位编号
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId == null ? null : partyId.trim();
    }

    /**
     * 取得 银行间账户类型，自营/理财
     * @return 银行间账户类型，自营/理财
     */
    public String getCfetsAcctType() {
        return cfetsAcctType;
    }

    /**
     * 设置 银行间账户类型，自营/理财
     * @param cfetsAcctType 银行间账户类型，自营/理财
     */
    public void setCfetsAcctType(String cfetsAcctType) {
        this.cfetsAcctType = cfetsAcctType == null ? null : cfetsAcctType.trim();
    }
}