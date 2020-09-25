package com.joyintech.tams.common.base.entity;

public class EtrDistribute extends EtrDistributeKey {
    /**
     * 接收状态
     */
    private String recStatus;

    /**
     * 交易主管，交易主管分发指令时记录主管的用户代码
     */
    private String tradingHeader;

    /**
     * 取得 接收状态
     * @return 接收状态
     */
    public String getRecStatus() {
        return recStatus;
    }

    /**
     * 设置 接收状态
     * @param recStatus 接收状态
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus == null ? null : recStatus.trim();
    }

    /**
     * 取得 交易主管，交易主管分发指令时记录主管的用户代码
     * @return 交易主管，交易主管分发指令时记录主管的用户代码
     */
    public String getTradingHeader() {
        return tradingHeader;
    }

    /**
     * 设置 交易主管，交易主管分发指令时记录主管的用户代码
     * @param tradingHeader 交易主管，交易主管分发指令时记录主管的用户代码
     */
    public void setTradingHeader(String tradingHeader) {
        this.tradingHeader = tradingHeader == null ? null : tradingHeader.trim();
    }
}