package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 当前时段
 */
public enum SSEBinaryExchangeStatus {
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
    OPEN_TRANSACTION("C"),
    /**
     * 连续交易
     */
    TRANSACTION("T"),
    /**
     * 已闭市
     */
    CLOSED("E"),
    /**
     * 收盘集合竞价
     */
    CLOSE_TRANSACTION("U"),
    /**
     * 停牌
     */
    HALT("P"),
    /**
     * 可恢复交易的熔断时段（盘中集合竞价）
     */
    RECOVERABLE_FUSE("M"),
    /**
     * 不可恢复交易的熔断时段（暂停交易至闭市）
     */
    UNRECOVERABLE_FUSE("N"),
    /**
     * 休市
     */
    BREAK("B"),
    /**
     * 集中撮合时段
     * <p>
     * 盘后固定价格
     * SecurityType = 3, MDStreamID= MD102
     */
    AFTER_CLOSE_TRANSACTION("A"),
    /**
     * 波动性中断
     * <p>
     * 期权类产品
     * SecurityType = 2, MDStreamID= MD301
     */
    VOLA("V"),
    /**
     * 启动
     * <p>
     * 盘后固定价格
     * SecurityType = 3, MDStreamID= MD102
     */
    START_FIXED_PRICE_AFTER_HOURS("I"),
    /**
     * 连续交易时段
     * <p>
     * 盘后固定价格
     * SecurityType = 3, MDStreamID= MD102
     */
    TRANSACTION_FIXED_PRICE_AFTER_HOURS("H"),
    /**
     * 已闭市
     * <p>
     * 盘后固定价格
     * SecurityType = 3, MDStreamID= MD102
     */
    CLOSED_FIXED_PRICE_AFTER_HOURS("D"),
    /**
     * 停牌
     * <p>
     * 盘后固定价格
     * SecurityType = 3, MDStreamID= MD102
     */
    HALT_FIXED_PRICE_AFTER_HOURS("F");

    private static final Map<String, SSEBinaryExchangeStatus> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SSEBinaryExchangeStatus.values()).forEach(status -> mapping.put(status.getStatus(), status));
    }

    private final String status;

    SSEBinaryExchangeStatus(String status) {
        this.status = status;
    }

    public static Optional<SSEBinaryExchangeStatus> lookup(String status) {
        return Optional.ofNullable(mapping.get(status));
    }

    public String getStatus() {
        return status;
    }
}
