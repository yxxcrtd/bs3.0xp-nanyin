package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SettleTransTgMapping extends BaseEntity {
    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 投管交易编号
     */
    private String tradeIdTg;

    /**
     * 投管交易数据状态（01：待处理、02：处理结束）
     */
    private String tgDataStatus;

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
     * 取得 投管交易编号
     * @return 投管交易编号
     */
    public String getTradeIdTg() {
        return tradeIdTg;
    }

    /**
     * 设置 投管交易编号
     * @param tradeIdTg 投管交易编号
     */
    public void setTradeIdTg(String tradeIdTg) {
        this.tradeIdTg = tradeIdTg == null ? null : tradeIdTg.trim();
    }

    /**
     * 取得 投管交易数据状态（01：待处理、02：处理结束）
     * @return 投管交易数据状态（01：待处理、02：处理结束）
     */
    public String getTgDataStatus() {
        return tgDataStatus;
    }

    /**
     * 设置 投管交易数据状态（01：待处理、02：处理结束）
     * @param tgDataStatus 投管交易数据状态（01：待处理、02：处理结束）
     */
    public void setTgDataStatus(String tgDataStatus) {
        this.tgDataStatus = tgDataStatus == null ? null : tgDataStatus.trim();
    }
}