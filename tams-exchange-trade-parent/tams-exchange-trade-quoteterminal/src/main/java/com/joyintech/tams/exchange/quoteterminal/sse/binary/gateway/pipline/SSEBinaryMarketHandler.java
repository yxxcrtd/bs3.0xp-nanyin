package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.SSEBinaryMessageFacade;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.Message;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.util.SSEBinaryUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class SSEBinaryMarketHandler extends SimpleChannelInboundHandler<Message> {

    private SSEBinaryMessageFacade facade;

    public SSEBinaryMarketHandler(SSEBinaryMessageFacade facade) {
        this.facade = facade;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
        facade.onMessage(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        facade.disconnected(String.format("server[%s] exit", SSEBinaryUtil.getRemoteAddress(ctx.channel())));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        facade.connected(String.format("connect to %s is success", SSEBinaryUtil.getRemoteAddress(ctx.channel())));
    }
}
