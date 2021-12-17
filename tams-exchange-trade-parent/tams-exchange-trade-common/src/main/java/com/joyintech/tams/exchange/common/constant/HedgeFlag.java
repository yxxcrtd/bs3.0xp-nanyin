package com.joyintech.tams.exchange.common.constant;

public enum HedgeFlag {
    Speculation("投机", "1"),
    Arbitrage("套利", "2"),
    Hedge("套保", "3"),
    MarketMaker("做市商", "5"),
    SpecHedge("第一腿投机第二腿套保 大商所专用", "6"),
    HedgeSpec("第一腿套保第二腿投机  大商所专用", "7");

    public final String value;
    public final String name;

    HedgeFlag(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
