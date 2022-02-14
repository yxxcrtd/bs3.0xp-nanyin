package com.joyintech.exchange.service.quote.enums;

public enum InterfSrcType {

    SH_FILE("SHFile", "上海文件行情"),
    SZ_BINARY("SZBinary", "深圳Binary行情");

    public String code;
    public String mean;

    InterfSrcType(String code, String mean) {
        this.code = code;
        this.mean = mean;
    }
}
