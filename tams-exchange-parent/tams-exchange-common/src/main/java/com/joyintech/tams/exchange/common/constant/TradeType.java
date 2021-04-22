package com.joyintech.tams.exchange.common.constant;

public enum TradeType {
    NORMAL("普通成交", 0),
    CANCEL("撤单成交", 1),
    INVALID("废单", 2),
    INNER_CANCEL("内部撤单", 3),
    CANCEL_INVALID("撤单废单", 4);

    public final int value;
    public final String name;

    TradeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static boolean probablyNoBrokerTradeId(int tradeType) {
        return tradeType == TradeType.CANCEL.value
                || tradeType == TradeType.INVALID.value
                || tradeType == TradeType.INNER_CANCEL.value
                || tradeType == TradeType.CANCEL_INVALID.value;
    }
}
