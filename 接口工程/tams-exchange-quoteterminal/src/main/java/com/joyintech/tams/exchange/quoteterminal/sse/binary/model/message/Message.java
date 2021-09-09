package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.BodyLength;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.MsgType;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.Tail;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Message {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);

    public static final Charset GBK = Charset.forName("GBK");

    protected MsgType msgType;

    private long sendingTime;

    private long msgSeqNum;

    private BodyLength bodyLength;

    private Tail checksum;

    public Message() {
    }

    Message(MsgType msgType) {
        this.msgType = msgType;
        this.sendingTime = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    }

    public Message(MsgType msgType, long sendingTime, long msgSeqNum, BodyLength bodyLength, Tail checksum) {
        this.msgType = msgType;
        this.sendingTime = sendingTime;
        this.msgSeqNum = msgSeqNum;
        this.bodyLength = bodyLength;
        this.checksum = checksum;
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
            byte[] msgTypeBytes = new byte[4];
            buffer.readBytes(msgTypeBytes);
            SSEBinaryMsgTypeEnum msgTypeEnum = SSEBinaryMsgTypeEnum.lookup(new String(msgTypeBytes))
                    .orElseThrow(() -> new IllegalArgumentException("unknown msg type: " + new String(msgTypeBytes)));
            this.msgType = new MsgType(msgTypeEnum);
            this.sendingTime = buffer.readLong();
            this.msgSeqNum = buffer.readLong();
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
     * 将该消息转换成SSEBinary协议.
     *
     * @return byte array
     */
    public byte[] toSSEBinaryMessage() {
        byte[] body = toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(24 + body.length + 4);
        buffer.put(msgType.toBytes());
        buffer.putLong(sendingTime);
        buffer.putLong(msgSeqNum);
        buffer.putInt(body.length);
        buffer.put(body);
        byte[] msgCompletePak = buffer.array();
        this.checksum = Tail.generateChecksum(msgCompletePak);
        byte[] checksumByte = checksum.toBytes();
        System.arraycopy(checksumByte, 0, msgCompletePak, msgCompletePak.length - 4, 4);
        return msgCompletePak;
    }

    public Tail getCheckSum() {
        if (checksum == null) {
            byte[] body = toBytes();
            ByteBuffer buffer = ByteBuffer.allocate(24 + body.length + 4);
            buffer.put(msgType.toBytes());
            buffer.putLong(sendingTime);
            buffer.putLong(msgSeqNum);
            buffer.putInt(body.length);
            buffer.put(body);
            byte[] hearBodyArray = buffer.array();
            this.checksum = Tail.generateChecksum(hearBodyArray);
        }
        return checksum;
    }

    public BodyLength getBodyLength() {
        if (bodyLength == null) {
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

    public long getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(long sendingTime) {
        this.sendingTime = sendingTime;
    }

    public long getMsgSeqNum() {
        return msgSeqNum;
    }

    public void setMsgSeqNum(long msgSeqNum) {
        this.msgSeqNum = msgSeqNum;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgType=" + msgType +
                ", sendingTime=" + sendingTime +
                ", msgSeqNum=" + msgSeqNum +
                ", bodyLength=" + bodyLength +
                ", checksum=" + checksum +
                '}';
    }
}
