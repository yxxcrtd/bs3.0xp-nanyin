package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlTradeKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 业务编号
     */
    private String busiNo;

    /**
     * 业务表，交易表、结算指令等
     */
    private String busiTable;

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
     * 取得 业务编号
     * @return 业务编号
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置 业务编号
     * @param busiNo 业务编号
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo == null ? null : busiNo.trim();
    }

    /**
     * 取得 业务表，交易表、结算指令等
     * @return 业务表，交易表、结算指令等
     */
    public String getBusiTable() {
        return busiTable;
    }

    /**
     * 设置 业务表，交易表、结算指令等
     * @param busiTable 业务表，交易表、结算指令等
     */
    public void setBusiTable(String busiTable) {
        this.busiTable = busiTable == null ? null : busiTable.trim();
    }
}