package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway;

import com.joyintech.tams.exchange.quoteterminal.szse.binary.config.SZExchangeConfigure;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 14:02 2021/8/20
 */
public class ConnectionListener implements ChannelFutureListener {
    private static final Logger logger = LoggerFactory.getLogger(ChannelFutureListener.class);

    private SZExchangeConfigure config;
    private SZSEBinaryMarketBootstrap binaryMarketBootstrap;

    public ConnectionListener(SZExchangeConfigure config, SZSEBinaryMarketBootstrap instance) {
        this.binaryMarketBootstrap = instance;
        this.config = config;
    }

    @Override
    public void operationComplete(ChannelFuture future) {
        if (future.isSuccess()) {
            binaryMarketBootstrap.setChannel(future.channel());
            logger.info("connect to server success -> {}:{}", config.getIp(), config.getPort());
        } else {
            logger.info("connect to server failure -> {}:{}", config.getIp(), config.getPort());
            future.channel().eventLoop().schedule(() -> binaryMarketBootstrap.connect(),
                    2, TimeUnit.SECONDS);
        }
    }
}
