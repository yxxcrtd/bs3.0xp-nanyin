package com.joyintech.tams.exchange.common.utils;

import com.google.common.base.Strings;

/**
 * @Author: think
 * @Description: 时间转换工具
 * @Date: Created in 14:30 2020/10/30
 */
public class TradeTimeConvertUtils {

    public static String convertTradeTime(String tradeTime) {
        if (Strings.isNullOrEmpty(tradeTime)) return null;
        if (tradeTime.length() == 7) {
            tradeTime = "0" + tradeTime;
        }
        return tradeTime;
    }
}
