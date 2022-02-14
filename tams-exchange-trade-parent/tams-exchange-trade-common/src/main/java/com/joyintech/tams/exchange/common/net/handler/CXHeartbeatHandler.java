package com.joyintech.tams.exchange.common.net.handler;

import com.joyintech.tams.exchange.common.net.protocol.ProtocolMessage;
import com.joyintech.tams.exchange.common.net.protocol.ProtocolMessageFactory;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yking on 2016/4/15 0015.
 */
public class CXHeartbeatHandler extends ChannelDuplexHandler {
    private static final Logger logger = LoggerFactory.getLogger(CXHeartbeatHandler.class);
    private static ProtocolMessage heartMsg = ProtocolMessageFactory.newHeartBeat();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;

            if (e.state() == IdleState.READER_IDLE) {
                logger.info("close client:{}", ctx.channel().remoteAddress());
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                //logger.info("Send heart beat: " + ctx.channel());
                ctx.channel().writeAndFlush(heartMsg);
            }
        }
    }
}
