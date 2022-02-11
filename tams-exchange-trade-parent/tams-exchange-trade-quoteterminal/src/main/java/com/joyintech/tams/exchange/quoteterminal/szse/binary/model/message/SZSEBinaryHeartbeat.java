package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.MsgType;
import io.netty.buffer.ByteBuf;

public class SZSEBinaryHeartbeat extends Message {

    public SZSEBinaryHeartbeat() {
        super(new MsgType(SZSEBinaryMsgType.HEARTBEAT));
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
        return "SZSEBinaryHeartbeat{" +
                "msgType=" + msgType +
                '}';
    }
}
