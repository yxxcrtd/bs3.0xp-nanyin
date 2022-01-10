package com.joyintech.tams.exchange.quoteterminal.szse.binary.util;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.*;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SZSEBinaryUtil {

    private SZSEBinaryUtil(){}

    private static Map<SZSEBinaryMsgType,Class<? extends Message>> pakMap;

    static {
        pakMap = new HashMap<>(16);
        pakMap.put(SZSEBinaryMsgType.HEARTBEAT, SZSEBinaryHeartbeat.class);
        pakMap.put(SZSEBinaryMsgType.LEVEL1, SZSEBinaryLevel1MarketSnapshot.class);
        pakMap.put(SZSEBinaryMsgType.CHANNEL_HEARTBEAT, SZSEBinaryMarketChannelHeartbeat.class);
        pakMap.put(SZSEBinaryMsgType.LOGON, SZSEBinaryLogon.class);
        pakMap.put(SZSEBinaryMsgType.LOGOUT, SZSEBinaryLogout.class);
    }

    public static Optional<Class<? extends Message>> getMessage(SZSEBinaryMsgType msgTypeEnum){
        if(msgTypeEnum == null){
            return Optional.empty();
        }
        return Optional.ofNullable(pakMap.get(msgTypeEnum));
    }

    public static String rightPad(String value, int size, char padChar){
        StringBuilder builder = new StringBuilder(value);
        for(int i = 0; i < size - value.length(); i++) {
            builder.append(padChar);
        }
        return builder.toString();
    }

    public static String getRemoteAddress(Channel channel){
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        int port = socketAddress.getPort();
        String hostName = socketAddress.getHostName();
        return hostName + ":" + port;
    }
}
