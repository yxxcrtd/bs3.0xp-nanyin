package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.BodyLength;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.MsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.Tail;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public abstract class Message {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);


    protected MsgType msgType;

    private BodyLength bodyLength;

    private Tail checksum;

    public Message() {
    }

    Message(MsgType msgType) {
        this.msgType = msgType;
    }

    /**
     * 将所有字段转为byte
     *
     * @return byte array
     */
    protected abstract byte[] toBytes();

    /**
     * 从字节流中恢复出对象
     *
     * @param body data array
     */
    protected abstract void read(ByteBuf body);

    /**
     * 从字节数组中恢复当前对象
     *
     * @param data 数据
     */
    public void resumeMessageFrom(byte[] data) {
        try {
            ByteBuf buffer = Unpooled.wrappedBuffer(data);
            int msgTypeCode = buffer.readInt();
            SZSEBinaryMsgType msgTypeEnum = SZSEBinaryMsgType.lookup(msgTypeCode)
                    .orElseThrow(() -> new IllegalArgumentException("unknown msg type: " + msgTypeCode));
            this.msgType = new MsgType(msgTypeEnum);
            this.bodyLength = new BodyLength(buffer.readInt());
            byte[] body = new byte[(int) bodyLength.getValue()];
            buffer.readBytes(body);
            read(Unpooled.wrappedBuffer(body));
            this.checksum = new Tail(buffer.readInt());
        } catch (Exception e) {
            logger.error("resumeMessage error: ", e);
        }
    }

    /**
     * 将该消息转换成SZSEBinary协议.
     *
     * @return byte array
     */
    public byte[] toSZSEBinaryMessage() {
        byte[] body = toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(8 + body.length + 4);
        buffer.putInt((int)msgType.getValue());
        buffer.putInt(body.length);
        buffer.put(body);
        byte[] msgCompletePak = buffer.array();
        this.checksum = Tail.generateChecksum(msgCompletePak);
        byte[] checksumByte = checksum.toBytes();
        System.arraycopy(checksumByte, 0, msgCompletePak, msgCompletePak.length - 4, 4);
        return msgCompletePak;
    }

    public Tail getCheckSum() {
        if(checksum == null) {
            byte[] body = toBytes();
            ByteBuffer buffer = ByteBuffer.allocate(4 + body.length + 4);
            buffer.putInt((int)msgType.getValue());
            buffer.putInt(body.length);
            buffer.put(body);
            byte[] hearBodyArray = buffer.array();
            this.checksum = Tail.generateChecksum(hearBodyArray);
        }
        return checksum;
    }

    public BodyLength getBodyLength() {
        if(bodyLength == null) {
            return new BodyLength(toBytes().length);
        }
        return bodyLength;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public void setBodyLength(BodyLength bodyLength) {
        this.bodyLength = bodyLength;
    }

    public Tail getChecksum() {
        return checksum;
    }

    public void setChecksum(Tail checksum) {
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgType=" + msgType +
                ", bodyLength=" + bodyLength +
                ", checksum=" + checksum +
                '}';
    }

    public Message(MsgType msgType, BodyLength bodyLength, Tail checksum) {
        this.msgType = msgType;
        this.bodyLength = bodyLength;
        this.checksum = checksum;
    }
}
