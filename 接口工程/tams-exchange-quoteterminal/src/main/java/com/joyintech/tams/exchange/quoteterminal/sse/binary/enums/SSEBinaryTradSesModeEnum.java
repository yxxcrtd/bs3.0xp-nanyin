package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;

/**
 * 交易盘交易模式: 1 - 系统测试; 2 - 模拟交易; 3 - 产品（正常交易）
 * uint8
 */
public enum SSEBinaryTradSesModeEnum {
    /**
     * 未知状态
     */
    UNKNOWN(0),
    /**
     * 系统测试
     */
    TEST(1),
    /**
     * 模拟交易
     */
    MOCK(2),
    /**
     * 产品（正常交易）
     */
    NOMORAL(3);

    private final int code;

    SSEBinaryTradSesModeEnum(int code) {
        this.code = code;
    }

    public static SSEBinaryTradSesModeEnum lookup(int code) {
        return Arrays.stream(SSEBinaryTradSesModeEnum.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElse(SSEBinaryTradSesModeEnum.UNKNOWN);
    }

    public int getCode() {
        return code;
    }
}
