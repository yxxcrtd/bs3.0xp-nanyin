package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SSEBinaryMarketPakEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) {
        byte[] data = message.toSSEBinaryMessage();
        byteBuf.writeBytes(data);
    }
}
