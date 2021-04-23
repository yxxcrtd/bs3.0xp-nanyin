package com.joyintech.tams.exchange.common.constant;

import java.util.HashSet;
import java.util.Set;

public enum Market {

    SZSE("深交所", "SZ", "04"),
    SHSE("上交所", "SH", "03"),

    SHFE("上海期货交易所", "SHFE", ""),
    DCE("大连期货交易所", "DCE", ""),
    CZCE("郑州期货交易所", "CZCE", ""),
    CFFEX("中国金融期货交易所", "CFFEX", "12"),
    INE("上海能源期货交易所", "INE", "");
    public final String value;//交易系统市场
    public final String investValue;//投管市场
    public final String name;

    Market(String name, String value, String investValue) {
        this.name = name;
        this.value = value;
        this.investValue = investValue;
    }

    private static Set<String> futuresMarket = new HashSet<>();

    static {
        futuresMarket.add(SHFE.value);
        futuresMarket.add(DCE.value);
        futuresMarket.add(CZCE.value);
        futuresMarket.add(CFFEX.value);
        futuresMarket.add(INE.value);
    }

    // 判断是否为为期货
    public static boolean isFutures(String market) {
        if (futuresMarket.contains(market)) {
            return true;
        } else {
            return false;
        }
    }
}
