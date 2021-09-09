package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.SSEBinaryMarketBootstrap;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.MsgType;
import io.netty.buffer.ByteBuf;


public class SSEBinaryHeartbeat extends Message {

    public SSEBinaryHeartbeat() {
        super(new MsgType(SSEBinaryMsgTypeEnum.HEARTBEAT));
        this.setMsgSeqNum(SSEBinaryMarketBootstrap.nxtOut.getAndIncrement());
    }

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {

    }

    @Override
    public String toString() {
        return "SSEBinaryHeartbeat{" +
                "msgType=" + msgType +
                '}';
    }
}
