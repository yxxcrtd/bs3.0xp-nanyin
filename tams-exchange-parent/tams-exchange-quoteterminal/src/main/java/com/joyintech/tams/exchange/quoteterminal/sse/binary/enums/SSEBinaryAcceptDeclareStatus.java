package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 产品当前阶段是否接受新订单申报
 */
public enum SSEBinaryAcceptDeclareStatus {
    /**
     * 未知状态
     */
    UNKNOWN("unknown"),
    /**
     * 不接受
     */
    UNACCEPT("0"),
    /**
     * 接受
     */
    ACCEPT("1");

    private static final Map<String, SSEBinaryAcceptDeclareStatus> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SSEBinaryAcceptDeclareStatus.values()).forEach(status -> mapping.put(status.getStatus(), status));
    }

    private final String status;

    SSEBinaryAcceptDeclareStatus(String status) {
        this.status = status;
    }

    public static Optional<SSEBinaryAcceptDeclareStatus> lookup(String status) {
        return Optional.ofNullable(mapping.get(status));
    }

    public String getStatus() {
        return status;
    }
}
