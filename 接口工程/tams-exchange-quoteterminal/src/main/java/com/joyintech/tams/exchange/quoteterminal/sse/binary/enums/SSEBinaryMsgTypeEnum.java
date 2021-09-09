package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public enum SSEBinaryMsgTypeEnum {
    /**
     * 集中竞价交易行情快照
     */
    LEVEL1("M002"),
    /**
     * 市场状态
     */
    MARKET_("M001"),
    /**
     * 登陆
     */
    LOGON("S001"),

    /**
     * 登出
     */
    LOGOUT("S002"),

    /**
     * 心跳
     */
    HEARTBEAT("S003");

    private static final Map<String, SSEBinaryMsgTypeEnum> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SSEBinaryMsgTypeEnum.values()).forEach(msgType -> mapping.put(msgType.getValue(), msgType));
    }

    private final String value;

    SSEBinaryMsgTypeEnum(String value) {
        this.value = value;
    }

    public static Optional<SSEBinaryMsgTypeEnum> lookup(String msgType) {
        return Optional.ofNullable(mapping.get(msgType));
    }

    public String getValue() {
        return value;
    }
}
