package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;

public enum SSEBinarySecurityStatus {
    /**
     * 未知状态
     */
    UNKNOWN("unknown"),
    /**
     * 不可正常交易
     */
    ABNORMAL("0"),
    /**
     * 可正常交易
     */
    NORMAL("1");

    private final String statusCode;

    SSEBinarySecurityStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public static SSEBinarySecurityStatus lookup(String statusCode) {
        return Arrays.stream(SSEBinarySecurityStatus.values())
                .filter(status -> status.getStatusCode().equalsIgnoreCase(statusCode))
                .findFirst()
                .orElse(SSEBinarySecurityStatus.UNKNOWN);
    }

    public String getStatusCode() {
        return statusCode;
    }
}
