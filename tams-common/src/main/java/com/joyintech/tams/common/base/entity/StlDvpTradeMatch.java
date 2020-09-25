package com.joyintech.tams.common.base.entity;

public class StlDvpTradeMatch extends StlDvpTradeMatchKey {
    /**
     * 是否汇总交易
     */
    private String isTotalTrade;

    /**
     * 取得 是否汇总交易
     * @return 是否汇总交易
     */
    public String getIsTotalTrade() {
        return isTotalTrade;
    }

    /**
     * 设置 是否汇总交易
     * @param isTotalTrade 是否汇总交易
     */
    public void setIsTotalTrade(String isTotalTrade) {
        this.isTotalTrade = isTotalTrade == null ? null : isTotalTrade.trim();
    }
}