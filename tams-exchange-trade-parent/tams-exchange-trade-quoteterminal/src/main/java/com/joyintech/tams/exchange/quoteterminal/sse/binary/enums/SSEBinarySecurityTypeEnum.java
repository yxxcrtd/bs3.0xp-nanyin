package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;

/**
 * 证券类型: 1 - 股票; 2 - 衍生品; 3 - 综合业务
 * uint8
 */
public enum SSEBinarySecurityTypeEnum {
    /**
     * 未知状态
     */
    UNKNOWN(0),
    /**
     * 股票
     */
    STOCK(1),
    /**
     * 衍生品
     */
    DERIVATIVES(2),
    /**
     * 综合业务
     */
    INTEGRATED(3);

    private final int code;

    SSEBinarySecurityTypeEnum(int code) {
        this.code = code;
    }

    public static SSEBinarySecurityTypeEnum lookup(int code) {
        return Arrays.stream(SSEBinarySecurityTypeEnum.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElse(SSEBinarySecurityTypeEnum.UNKNOWN);
    }

    public int getCode() {
        return code;
    }
}
