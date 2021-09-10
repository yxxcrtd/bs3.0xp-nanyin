package com.joyintech.tams.exchange.quoteterminal.sse.file.cache;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 11:25 2021/8/11
 */
public class LimitPrice {

    private static Map<String, Double> upperLimitPriceMap = Maps.newHashMap();//涨停价

    private static Map<String, Double> lowerLimitPriceMap = Maps.newHashMap();//跌停价

    public static void addUpperLimitPrice(String stockCode, Double upperLimitPrice) {
        upperLimitPriceMap.put(stockCode, upperLimitPrice);
    }

    public static double getUpperLimitPrice(String stockCode) {
        Double price = upperLimitPriceMap.get(stockCode);
        return Objects.nonNull(price) ? price : 99999999;
    }

    public static void addLowerLimitPrice(String stockCode, Double lowerLimitPrice) {
        lowerLimitPriceMap.put(stockCode, lowerLimitPrice);
    }

    public static double getLowerLimitPrice(String stockCode) {
        Double price = lowerLimitPriceMap.get(stockCode);
        return Objects.nonNull(price) ? price : 0;
    }
}
