package com.joyintech.tams.exchange.quoteterminal.szse.binary.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public enum SZSEBinaryMsgType {
    /**
     * 集中竞价交易行情快照
     */
    LEVEL1(300111),
    /**
     * 行情 频道心跳
     */
    CHANNEL_HEARTBEAT(390095),
    /**
     * 登陆
     */
    LOGON(1),

    /**
     * 登出
     */
    LOGOUT(2),

    /**
     * 连接心跳
     */
    HEARTBEAT(3);

    private static final Map<Integer, SZSEBinaryMsgType> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SZSEBinaryMsgType.values()).forEach(msgType -> mapping.put(msgType.getCode(), msgType));
    }

    private final int code;

    SZSEBinaryMsgType(int code) {
        this.code = code;
    }

    public static Optional<SZSEBinaryMsgType> lookup(int msgType) {
        return Optional.ofNullable(mapping.get(msgType));
    }

    public int getCode() {
        return code;
    }

    public static boolean isHeartBeat(int code) {
        return HEARTBEAT.getCode() == code;
    }
}
