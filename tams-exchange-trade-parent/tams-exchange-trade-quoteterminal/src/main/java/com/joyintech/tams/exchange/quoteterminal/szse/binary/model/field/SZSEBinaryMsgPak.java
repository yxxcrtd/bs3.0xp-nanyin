package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;

import java.nio.ByteBuffer;

/**
 * 协议消息
 */
public class SZSEBinaryMsgPak {
    private final MsgType msgType;

    private final byte[] msgPak;

    private final Tail checksum;

    public SZSEBinaryMsgPak(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int msgTypeCode = buffer.getInt();
        SZSEBinaryMsgType msgTypeEnum = SZSEBinaryMsgType.lookup(msgTypeCode)
                .orElseThrow(() -> new IllegalArgumentException("unknown msg type -> " + msgTypeCode));
        this.msgType = new MsgType(msgTypeEnum);
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

    public byte[] getMsgPak() {
        return msgPak;
    }

    public Tail getChecksum() {
        return checksum;
    }
}
