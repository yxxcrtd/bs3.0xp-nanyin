package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 行情条目类别
 */
public enum SSEBinaryMDEntryTypeEnum {
    /**
     * 未知
     */
    UNKNOWN(""),
    /**
     * 买入
     */
    BUY("0"),
    /**
     * 卖出
     */
    SELL("1"),
    /**
     * 最新成交价
     */
    LAST("2"),
    /**
     * 今开盘价
     */
    OPEN("4"),
    /**
     * 今收盘价
     */
    CLOSE("5"),
    /**
     * 今结算价
     */
    SETTLE("6"),
    /**
     * 当日最高成交价
     */
    HIGH("7"),
    /**
     * 当日最低成交价
     */
    LOW("8"),
    /**
     * IOPV
     */
    IOPV("v"),
    /**
     * 前一日 IOPV
     */
    PRE_IOPV("w"),
    /**
     * 动态参考价格及虚拟匹配数量
     */
    DYNAMIC("x"),
    /**
     * 昨日结算价
     */
    PRE_SETTLE("z1"),
    /**
     * 总持仓量
     */
    ALL_POSITION("z2");

    private static final Map<String, SSEBinaryMDEntryTypeEnum> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SSEBinaryMDEntryTypeEnum.values()).forEach(type -> mapping.put(type.getMDEntryType(), type));
    }

    private final String mdEntryType;

    SSEBinaryMDEntryTypeEnum(String mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    public static Optional<SSEBinaryMDEntryTypeEnum> lookup(String priceType) {
        return Optional.ofNullable(mapping.get(priceType));
    }

    public String getMDEntryType() {
        return mdEntryType;
    }
}
