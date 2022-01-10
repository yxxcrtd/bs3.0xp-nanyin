package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.SSEBinaryHeartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSEBinaryReadIdleStateHandler extends IdleStateHandler {

    private static final Logger logger = LoggerFactory.getLogger(SSEBinaryReadIdleStateHandler.class);

    private SSEBinaryHeartbeat heartbeat = new SSEBinaryHeartbeat();

    public SSEBinaryReadIdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
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
