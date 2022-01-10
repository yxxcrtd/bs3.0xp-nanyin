package com.joyintech.exchange.service.quote.sh.file.enums;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 16:03 2021/8/10
 */
public enum SSEFileQuoteType {
    STOCK(1, "stock", "股票行情"),
    BOND(2, "bond", "债券行情"),
    OPTION(3, "option", "期权行情");

    public int code;
    public String value;
    public String mean;

    SSEFileQuoteType(int code, String value, String mean) {
        this.code = code;
        this.value = value;
        this.mean = mean;
    }
}
