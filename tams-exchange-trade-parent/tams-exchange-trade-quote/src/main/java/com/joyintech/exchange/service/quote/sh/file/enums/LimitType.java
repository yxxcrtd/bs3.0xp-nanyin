package com.joyintech.exchange.service.quote.sh.file.enums;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 17:24 2021/8/24
 */
public enum LimitType {
    HAS_LIMIT("N", "交易规则（2013 修订版）3.4.13 规定的有涨跌幅限制类型或者权证管理办法第 22 条规定"),
    NOT_LIMIT("R", "交易规则（2013 修订版）3.4.15 和 3.4.16规定的无涨跌幅限制类型"),
    REPURCHASE("S", "回购涨跌幅控制类型"),
    CONSULT_PRICE("F", "基于参考价格的涨跌幅控制"),
    IPO("P", "IPO上市首日的涨跌幅控制类型"),
    NO_LIMIT_PRICE("U", "无任何价格涨跌幅控制类型");

    public String code;
    public String mean;

    LimitType(String code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public static boolean isNotLimitPrice(String limitType) {
        return NOT_LIMIT.code.equals(limitType) || NO_LIMIT_PRICE.code.equals(limitType);
    }
}
