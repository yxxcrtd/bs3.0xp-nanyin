package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.MsgType;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.Text;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SSEBinaryLogout extends Message {

    private long sessionStatus;

    private Text text;

    public SSEBinaryLogout() {
        super(new MsgType(SSEBinaryMsgTypeEnum.LOGOUT));
    }

    @Override
    protected byte[] toBytes() {
        byte[] textByte = text.toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(4 + textByte.length);
        buffer.putInt((int) sessionStatus);
        buffer.put(textByte);
        return buffer.array();
    }

    @Override
    protected void read(ByteBuf body) {
        this.sessionStatus = body.readUnsignedInt();
        this.text = new Text(body.readCharSequence(Text.FIELD_LENGTH, Message.GBK).toString());
    }

    @Override
    public String toString() {
        return "SSEBinaryLogout{" +
                "sessionStatus=" + sessionStatus +
                ", text=" + text +
                '}';
    }

    public long getSessionStatus() {
        return sessionStatus;
    }

    public Text getText() {
        return text;
    }
}
