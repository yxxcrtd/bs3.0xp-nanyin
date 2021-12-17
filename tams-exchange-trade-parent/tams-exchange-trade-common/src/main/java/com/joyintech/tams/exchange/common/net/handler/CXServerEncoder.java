package com.joyintech.tams.exchange.common.net.handler;


import com.joyintech.tams.exchange.common.net.protocol.ProtocolMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by yking on 2016/4/15 0015.
 */
public class CXServerEncoder extends MessageToByteEncoder<ProtocolMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolMessage msg, ByteBuf out) throws Exception {
        msg.writeBuffer(out);
    }
}

