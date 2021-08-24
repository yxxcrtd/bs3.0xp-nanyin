package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.SZSEBinaryHeartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SZSEBinaryReadIdleStateHandler extends IdleStateHandler {

    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryReadIdleStateHandler.class);

    private SZSEBinaryHeartbeat heartbeat = new SZSEBinaryHeartbeat();

    public SZSEBinaryReadIdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        ctx.channel().writeAndFlush(heartbeat);
        logger.info("send heartbeat ... ");
        try {
            super.channelIdle(ctx, evt);
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
