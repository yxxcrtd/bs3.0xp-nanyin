package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrDistributeInterfTmpKey extends BaseEntity {
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 指定交易员
     */
    private String trader;

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
     * 取得 指定交易员
     * @return 指定交易员
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 指定交易员
     * @param trader 指定交易员
     */
    public void setTrader(String trader) {
        this.trader = trader == null ? null : trader.trim();
    }
}