package com.joyintech.tams.exchange.terminal.trade.jsd;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MessageManager;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req.ReqHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespProtocol;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageHandler extends SimpleChannelInboundHandler<RespProtocol> {

    private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    private JSDTradeApi jsdTradeApi;

    public MessageHandler(JSDTradeApi tradeApi){
        this.jsdTradeApi = tradeApi;
        this.jsdTradeApi.msgTag = 0;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("server:{} channel active", ctx.channel().remoteAddress().toString());
        jsdTradeApi.jsdChannelActive();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RespProtocol s) throws Exception {
        jsdTradeApi.channelRead0(channelHandlerContext, s);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("server:{} channel inactive", ctx.channel().remoteAddress().toString());
        jsdTradeApi.jsdChannelInactive();
    }
}
