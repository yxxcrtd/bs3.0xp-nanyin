package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.*;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SZSEBinaryLogon extends Message {
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
    private int heartbeatInterval;

    /**
     * 密码
     */
    private Password password;

    /**
     * 二进制协议版本
     */
    private DefaultApplVerID version;

    public SZSEBinaryLogon(String password, String senderCompId, String targetCompId, int heartbeatInterval, String version) {
        super(new MsgType(SZSEBinaryMsgType.LOGON));
        this.password = new Password(password);
        this.senderCompId = new SenderCompId(senderCompId);
        this.targetCompId = new TargetCompId(targetCompId);
        this.heartbeatInterval = heartbeatInterval;
        this.version = new DefaultApplVerID(version);
    }

    public SZSEBinaryLogon() {
    }

    @Override
    protected byte[] toBytes() {
        byte[] pwdArray = password.toBytes();
        byte[] sendIdArray = senderCompId.toBytes();
        byte[] targetIdArray = targetCompId.toBytes();
        byte[] versionArray = version.toBytes();
        int bodyLength = pwdArray.length + sendIdArray.length + targetIdArray.length + versionArray.length + 4;
        ByteBuffer buffer = ByteBuffer.allocate(bodyLength);
        buffer.put(sendIdArray)
                .put(targetIdArray)
                .putInt(heartbeatInterval)
                .put(pwdArray)
                .put(versionArray);
        return buffer.array();
    }

    @Override
    protected void read(ByteBuf body) {
        // 发送方编号
        this.senderCompId = new SenderCompId(body.readCharSequence(SenderCompId.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        // 对方编号
        this.targetCompId = new TargetCompId(body.readCharSequence(TargetCompId.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        // 心跳间隔
        this.heartbeatInterval = body.readInt();
        // 密码
        this.password = new Password(body.readCharSequence(Password.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        // 版本信息
        this.version = new DefaultApplVerID(body.readCharSequence(DefaultApplVerID.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
    }

    @Override
    public String toString() {
        return "SZSEBinaryLogon{" +
                "password=" + password.getValue() +
                ", senderCompId=" + senderCompId.getValue() +
                ", targetCompId=" + targetCompId.getValue() +
                ", heartbeatInterval=" + heartbeatInterval +
                ", version=" + version.getValue() +
                '}';
    }
}
