package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.MsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.Text;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SZSEBinaryLogout extends Message{

    private int sessionStatus;

    private Text text;

    public SZSEBinaryLogout() {
        super(new MsgType(SZSEBinaryMsgType.LOGOUT));
    }

    @Override
    protected byte[] toBytes() {
        byte[] textByte = text.toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(4 + textByte.length);
        buffer.putInt(sessionStatus);
        buffer.put(textByte);
        return buffer.array();
    }

    @Override
    protected void read(ByteBuf body) {
        this.sessionStatus = body.readInt();
        this.text = new Text(body.readCharSequence(Text.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
    }

    @Override
    public String toString() {
        return "SZSEBinaryLogout{" +
                "sessionStatus=" + sessionStatus +
                ", text=" + text +
                '}';
    }

    public int getSessionStatus() {
        return sessionStatus;
    }

    public Text getText() {
        return text;
    }
}
