package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.SZSEBinaryMarketBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SZSEBinaryHeartbeatLostHandler extends IdleStateHandler {

    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryHeartbeatLostHandler.class);

    public SZSEBinaryHeartbeatLostHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        logger.info("心跳超时 ... 准备重连");
        SZSEBinaryMarketBootstrap bootstrap = SZSEBinaryMarketBootstrap.getInstance(null, null);
        bootstrap.keepReconnect();
    }
}
