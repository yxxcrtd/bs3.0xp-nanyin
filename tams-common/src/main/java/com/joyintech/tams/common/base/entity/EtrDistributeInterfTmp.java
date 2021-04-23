package com.joyintech.tams.common.base.entity;

public class EtrDistributeInterfTmp extends EtrDistributeInterfTmpKey {
    /**
     * 交易主管，交易主管分发指令时记录主管的用户代码
     */
    private String tradingHeader;

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