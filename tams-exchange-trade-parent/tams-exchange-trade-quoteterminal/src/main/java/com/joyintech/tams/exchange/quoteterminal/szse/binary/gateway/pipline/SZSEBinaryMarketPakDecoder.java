package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.codec.SZSEBinaryMessageSerializer;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.Tail;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.Message;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.util.SZSEBinaryUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class SZSEBinaryMarketPakDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) {
        int msgType = byteBuf.getInt(0);
        int bodyLength = byteBuf.getInt(4);
        long checksum = byteBuf.getUnsignedInt(8 + bodyLength);
        byte[] data = new byte[8 + bodyLength + 4];
        byteBuf.readBytes(data);
        //检查这条数据的checksum算法是否与我本地算法一致.
        if(checksum != Tail.generateChecksum(data).getValue()) {
            return;
        }
        SZSEBinaryMessageSerializer serializer = new SZSEBinaryMessageSerializer();
        SZSEBinaryMsgType.lookup(msgType)
                .flatMap(SZSEBinaryUtil::getMessage)
                .ifPresent(msgClass -> {
                    Message message = serializer.deserialize(msgClass, data);
                    out.add(message);
                });
    }
}
