package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlSecAcctKey extends BaseEntity {
    /**
     * 账户账号，存网点账号
     */
    private String acctNo;

    /**
     * 账套代码
     */
    private String portfolioId;

    /**
     * 取得 账户账号，存网点账号
     * @return 账户账号，存网点账号
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 账户账号，存网点账号
     * @param acctNo 账户账号，存网点账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 账套代码
     * @param portfolioId 账套代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }
}