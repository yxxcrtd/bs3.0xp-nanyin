package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.ApplLastSeqNum;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.ChannelNo;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.EndOfChannel;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

/**
 * 行情数据频道心跳消息
 */
public class SZSEBinaryMarketChannelHeartbeat extends Message{

    private ChannelNo channelNo;

    private ApplLastSeqNum applLastSeqNum;

    private EndOfChannel endOfChannel;

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {
        this.channelNo = new ChannelNo(body.readUnsignedShort());
        this.applLastSeqNum = new ApplLastSeqNum(body.readUnsignedShort());
        this.endOfChannel = new EndOfChannel(body.readUnsignedShort());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SZSEBinaryMarketChannelHeartbeat)) return false;
        SZSEBinaryMarketChannelHeartbeat that = (SZSEBinaryMarketChannelHeartbeat) object;
        return Objects.equals(channelNo, that.channelNo) &&
                Objects.equals(applLastSeqNum, that.applLastSeqNum) &&
                Objects.equals(endOfChannel, that.endOfChannel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelNo, applLastSeqNum, endOfChannel);
    }

    @Override
    public String toString() {
        return "SZSEBinaryMarketChannelHeartbeat{" +
                "channelNo=" + channelNo +
                ", applLastSeqNum=" + applLastSeqNum +
                ", endOfChannel=" + endOfChannel +
                '}';
    }
}
