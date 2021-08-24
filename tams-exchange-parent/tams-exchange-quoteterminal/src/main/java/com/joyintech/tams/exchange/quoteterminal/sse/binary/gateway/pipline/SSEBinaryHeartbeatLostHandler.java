package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.SSEBinaryMarketBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSEBinaryHeartbeatLostHandler extends IdleStateHandler {

    private static final Logger logger = LoggerFactory.getLogger(SSEBinaryHeartbeatLostHandler.class);

    public SSEBinaryHeartbeatLostHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        logger.info("心跳超时 ... 准备重连");
        SSEBinaryMarketBootstrap bootstrap = SSEBinaryMarketBootstrap.getInstance(null, null);
        bootstrap.keepReconnect();
    }
}
