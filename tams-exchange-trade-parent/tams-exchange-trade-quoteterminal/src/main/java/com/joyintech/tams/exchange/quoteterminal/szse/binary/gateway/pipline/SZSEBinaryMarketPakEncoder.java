package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline;

import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SZSEBinaryMarketPakEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) {
        byte[] data = message.toSZSEBinaryMessage();
        byteBuf.writeBytes(data);
    }
}
