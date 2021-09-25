package com.joyintech.tams.exchange.quoteterminal.szse.binary.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public enum SZSEBinaryExchangeStatus {
    /**
     * 未知
     */
    UNKNOWN("unknown"),
    /**
     * 启动（开市前）
     */
    START("S"),
    /**
     * 开盘集合竞价
     */
    OPEN_TRANSACTION("O"),
    /**
     * 连续竞价
     */
    TRANSACTION("T"),
    /**
     * 休市
     */
    BREAK("B"),
    /**
     * 收盘集合竞价
     */
    CLOSE_TRANSACTION("C"),
    /**
     * 已闭市
     */
    CLOSED("E"),
    /**
     * 临时停牌
     */
    HALT("H"),
    /**
     * 盘后交易
     */
    AFTER_CLOSE_TRANSACTION("A"),
    /**
     * 波动性中断
     */
    VOLA("V");

    private static final Map<String, SZSEBinaryExchangeStatus> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SZSEBinaryExchangeStatus.values()).forEach(exchangeStatus -> mapping.put(exchangeStatus.getStatus(), exchangeStatus));
    }

    private final String status;

    SZSEBinaryExchangeStatus(String status) {
        this.status = status;
    }

    public static Optional<SZSEBinaryExchangeStatus> lookup(String status) {
        return Optional.ofNullable(mapping.get(status));
    }

    public String getStatus() {
        return status;
    }
}
