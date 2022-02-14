package com.joyintech.tams.exchange.common.net.handler;


import com.joyintech.tams.exchange.common.net.agent.Agent;
import com.joyintech.tams.exchange.common.net.protocol.ProtocolMessage;
import com.joyintech.tams.exchange.common.utils.SpringContextUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created by yking on 2016/4/15 0015.
 */
public class CXServerHandler extends SimpleChannelInboundHandler<ProtocolMessage> {
    private static final Logger logger = LoggerFactory.getLogger(CXServerHandler.class);

    private Class<? extends Agent> agentCls;

    private Agent agent;

    public CXServerHandler(Class<? extends Agent> agentCls) {
        this.agentCls = agentCls;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolMessage msg) throws Exception {
        agent.handleMessage(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("Server channel active:{}", ctx.channel());
        agent = SpringContextUtils.getBean(agentCls);
        agent.connEstablished(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        if (Objects.nonNull(agent)) agent.connClosed();
        logger.debug("Server channelClosed:{}", ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        logger.debug("channelReadComplete:{}", ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("exception caught:{}", cause.getMessage());
    }
}
