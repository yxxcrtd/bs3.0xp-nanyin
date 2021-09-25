package com.joyintech.tams.exchange.quoteterminal.szse.binary.enums;


import java.util.Arrays;

public enum SZSEBinarySecurityStatus {
    /**
     * 未知状态
     */
    UNKNOWN("unknown"),
    /**
     * 正常状态
     */
    NORMAL("0"),
    /**
     * 全天停牌
     */
    HALT_DAY("1");

    private final String statusCode;

    SZSEBinarySecurityStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public static SZSEBinarySecurityStatus lookup(String statusCode) {
        return Arrays.stream(SZSEBinarySecurityStatus.values())
                .filter(status -> status.getStatusCode().equalsIgnoreCase(statusCode))
                .findFirst()
                .orElse(SZSEBinarySecurityStatus.UNKNOWN);
    }

    public String getStatusCode() {
        return statusCode;
    }
}
