package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.SZSEBinaryMessageFacade;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class SZSEBinaryMarketHandler extends SimpleChannelInboundHandler<Message> {
    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryMarketHandler.class);

    private SZSEBinaryMessageFacade facade;

    public SZSEBinaryMarketHandler(SZSEBinaryMessageFacade facade) {
        this.facade = facade;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
        facade.onMessage(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        facade.disconnected(String.format("server[%s]", ctx.channel().remoteAddress().toString()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        facade.connected(String.format("connect to %s is success", ctx.channel().remoteAddress().toString()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        logger.debug("channelReadComplete:{}", ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("exception caught:{}", cause.getMessage());
    }
}
