package com.joyintech.tams.exchange.common.constant;

public enum TradePlat {
    
     NORMAL("02", "沪深竞价撮合平台"),
     SH_FIXED_INCOME("03", "上海固定收益平台"),
    SH_SCALE("04", "上海大宗交易平台"),
    SZ_PROTOCOL("05", "深圳综合协议平台");

    public final String value;
    public final String name;

    TradePlat(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public static boolean needSettleTrade(String tradePlat) {
        return TradePlat.SH_FIXED_INCOME.value.equals(tradePlat)
                || TradePlat.SH_SCALE.value.equals(tradePlat)
                || TradePlat.SZ_PROTOCOL.value.equals(tradePlat);
    }
}
