package com.joyintech.tams.exchange.common.constant;

public enum BatchPriceMode {
    FIX_AND_INCREASE("固定价格递增", 1),
    BID_OFFSET("买一价偏移", 2),
    ASK_OFFSET("卖一价偏移", 3),
    LAST_OFFSET("最新价偏移", 4),
    LAST_PRICE("市价", 5);

    public final String name;
    public final int value;

    BatchPriceMode(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getBatchPriceMode(int value) {
        for (BatchPriceMode priceMode : values()) {
            if (priceMode.value == value) {
                return priceMode.name;
            }
        }
        return null;
    }
}
