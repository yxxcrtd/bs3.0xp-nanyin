package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.codec.SSEBinaryMessageSerializer;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.Tail;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.Message;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.util.SSEBinaryUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class SSEBinaryMarketPakDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) {
        byte[] msgTypeBytes = new byte[4];
        byteBuf.readBytes(msgTypeBytes);
        int bodyLength = byteBuf.getInt(4);
        long checksum = byteBuf.getUnsignedInt(8 + bodyLength);
        byte[] data = new byte[8 + bodyLength + 4];
        byteBuf.readBytes(data);
        //检查这条数据的checksum算法是否与我本地算法一致.
        if (checksum != Tail.generateChecksum(data).getValue()) {
            return;
        }
        SSEBinaryMessageSerializer serializer = new SSEBinaryMessageSerializer();
        SSEBinaryMsgTypeEnum.lookup(new String(msgTypeBytes))
                .flatMap(SSEBinaryUtil::getMessage)
                .ifPresent(msgClass -> {
                    Message message = serializer.deserialize(msgClass, data);
                    out.add(message);
                });
    }
}
