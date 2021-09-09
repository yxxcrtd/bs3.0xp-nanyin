package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;

/**
 * 上市状态
 */
public enum SSEBinaryListedStatus {
    /**
     * 未知状态
     */
    UNKNOWN("unknown"),
    /**
     * 未上市
     */
    UNLISTED("0"),
    /**
     * 已上市
     */
    LISTED("1");

    private final String statusCode;

    SSEBinaryListedStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public static SSEBinaryListedStatus lookup(String statusCode) {
        return Arrays.stream(SSEBinaryListedStatus.values())
                .filter(status -> status.getStatusCode().equalsIgnoreCase(statusCode))
                .findFirst()
                .orElse(SSEBinaryListedStatus.UNKNOWN);
    }

    public String getStatusCode() {
        return statusCode;
    }
}
