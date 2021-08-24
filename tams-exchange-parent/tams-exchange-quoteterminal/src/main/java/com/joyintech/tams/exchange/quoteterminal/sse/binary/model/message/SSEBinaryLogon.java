package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.ApplVerID;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.MsgType;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.SenderCompId;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.TargetCompId;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

public class SSEBinaryLogon extends Message {
    /**
     * 发送方代码
     */
    private SenderCompId senderCompId;
    /**
     * 接收方代码
     */
    private TargetCompId targetCompId;
    /**
     * 心跳间隔
     */
    private int heartBtInt;
    /**
     * 协议版本
     */
    private ApplVerID applVerId;

    public SSEBinaryLogon(String senderCompId, String targetCompId, int heartBtInt, String version) {
        super(new MsgType(SSEBinaryMsgTypeEnum.LOGON));
        this.senderCompId = new SenderCompId(senderCompId);
        this.targetCompId = new TargetCompId(targetCompId);
        this.heartBtInt = heartBtInt;
        this.applVerId = new ApplVerID(version);
    }

    public SSEBinaryLogon() {
    }

    @Override
    protected byte[] toBytes() {
        byte[] sendIdArray = senderCompId.toBytes();
        byte[] targetIdArray = targetCompId.toBytes();
        byte[] versionArray = applVerId.toBytes();
        int bodyLength = sendIdArray.length + targetIdArray.length + versionArray.length + 4;
        ByteBuffer buffer = ByteBuffer.allocate(bodyLength);
        buffer.put(sendIdArray)
                .put(targetIdArray)
                .putShort((short) heartBtInt)
                .put(versionArray);
        return buffer.array();
    }

    @Override
    protected void read(ByteBuf body) {
        // 发送方编号
        this.senderCompId = new SenderCompId(body.readCharSequence(SenderCompId.FIELD_LENGTH, Message.GBK).toString());
        // 对方编号
        this.targetCompId = new TargetCompId(body.readCharSequence(TargetCompId.FIELD_LENGTH, Message.GBK).toString());
        // 心跳间隔
        this.heartBtInt = body.readUnsignedShort();
        // 版本信息
        this.applVerId = new ApplVerID(body.readCharSequence(ApplVerID.FIELD_LENGTH, Message.GBK).toString());
    }

    @Override
    public String toString() {
        return "SSEBinaryLogon{" +
                ", senderCompId=" + senderCompId.getValue() +
                ", targetCompId=" + targetCompId.getValue() +
                ", heartBtInt=" + heartBtInt +
                ", ApplVerId=" + applVerId.getValue() +
                '}';
    }
}
