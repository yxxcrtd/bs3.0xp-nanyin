package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;

import java.nio.ByteBuffer;

/**
 * 协议消息
 */
public class SSEBinaryMsgPak {
    private final MsgType msgType;

    private long sendingTime;

    private long msgSeqNum;

    private final byte[] msgPak;

    private final Tail checksum;

    public SSEBinaryMsgPak(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        byte[] msgTypeBytes = new byte[4];
        buffer.get(msgTypeBytes);
        SSEBinaryMsgTypeEnum msgTypeEnum = SSEBinaryMsgTypeEnum.lookup(new String(msgTypeBytes))
                .orElseThrow(() -> new IllegalArgumentException("unknown msg type -> " + new String(msgTypeBytes)));
        this.msgType = new MsgType(msgTypeEnum);
        this.sendingTime = buffer.getLong();
        this.msgSeqNum = buffer.getLong();
        int bodyLength = buffer.getInt();
        byte[] body = new byte[bodyLength];
        buffer.get(body);
        this.checksum = new Tail(buffer.getInt());
        this.msgPak = data;
    }


    /**
     * 检查checksum值
     *
     * @return bool
     */
    public boolean validateChecksum() {
        if (checksum == null || msgPak.length == 0) {
            return false;
        }
        Tail checksum = Tail.generateChecksum(msgPak);
        return checksum.getValue() == this.checksum.getValue();
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public long getSendingTime() {
        return sendingTime;
    }

    public long getMsgSeqNum() {
        return msgSeqNum;
    }

    public byte[] getMsgPak() {
        return msgPak;
    }

    public Tail getChecksum() {
        return checksum;
    }
}
