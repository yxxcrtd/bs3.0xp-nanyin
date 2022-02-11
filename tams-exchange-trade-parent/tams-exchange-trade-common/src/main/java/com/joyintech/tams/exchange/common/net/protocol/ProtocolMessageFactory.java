package com.joyintech.tams.exchange.common.net.protocol;


import com.joyintech.tams.exchange.common.utils.JsonUtils;

public class ProtocolMessageFactory {
    private static ProtocolMessage heartBeatMsg = new ProtocolMessage(FunctionCode.HEARTBEAT.value, 0, 0, "");
    private static ProtocolMessage ackMsg = new ProtocolMessage(FunctionCode.ACK.value, 0, 0, "");
    private static ProtocolMessage templateMsg = new ProtocolMessage(FunctionCode.ACK.value, 0, 0, null);

    public static ProtocolMessage newHeartBeat() {
        return heartBeatMsg;
    }

    public static ProtocolMessage newHeartBeatAck() {
        return ackMsg;
    }

    public static ProtocolMessage newMessage(int fc, long reqId, int dataType, String body) {
        ProtocolMessage protocolMessage = templateMsg.clone();
        protocolMessage.init(fc, reqId, dataType, body);
        return protocolMessage;
    }

    public static ProtocolMessage newMessage(int fc, long reqId, int dataType, byte[] body) {
        ProtocolMessage protocolMessage = templateMsg.clone();
        protocolMessage.init(fc, reqId, dataType, body);
        return protocolMessage;
    }

    public static ProtocolMessage newMessage(int fc, long reqId, int dataType, Object object) {
        ProtocolMessage protocolMessage = templateMsg.clone();
        protocolMessage.init(fc, reqId, dataType, JsonUtils.getJsonFromObject(object));
        return protocolMessage;
    }
}
