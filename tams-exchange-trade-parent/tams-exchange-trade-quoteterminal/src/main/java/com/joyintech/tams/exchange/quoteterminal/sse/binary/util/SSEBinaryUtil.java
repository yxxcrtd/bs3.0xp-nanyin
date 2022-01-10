package com.joyintech.tams.exchange.quoteterminal.sse.binary.util;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.*;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SSEBinaryUtil {

    private static final Map<SSEBinaryMsgTypeEnum, Class<? extends Message>> pakMap;

    static {
        pakMap = new HashMap<>(16);
        pakMap.put(SSEBinaryMsgTypeEnum.HEARTBEAT, SSEBinaryHeartbeat.class);
        pakMap.put(SSEBinaryMsgTypeEnum.LEVEL1, SSEBinaryLevel1MarketSnapshot.class);
        pakMap.put(SSEBinaryMsgTypeEnum.LOGON, SSEBinaryLogon.class);
        pakMap.put(SSEBinaryMsgTypeEnum.LOGOUT, SSEBinaryLogout.class);
    }

    private SSEBinaryUtil() {
    }

    public static Optional<Class<? extends Message>> getMessage(SSEBinaryMsgTypeEnum msgTypeEnum) {
        if (msgTypeEnum == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(pakMap.get(msgTypeEnum));
    }

    public static String rightPad(String value, int size, char padChar) {
        StringBuilder builder = new StringBuilder(value);
        for (int i = 0; i < size - value.length(); i++) {
            builder.append(padChar);
        }
        return builder.toString();
    }

    public static String getRemoteAddress(Channel channel) {
        InetSocketAddress socketAddress = (InetSocketAddress) channel.remoteAddress();
        int port = socketAddress.getPort();
        String hostName = socketAddress.getHostName();
        return hostName + ":" + port;
    }
}
