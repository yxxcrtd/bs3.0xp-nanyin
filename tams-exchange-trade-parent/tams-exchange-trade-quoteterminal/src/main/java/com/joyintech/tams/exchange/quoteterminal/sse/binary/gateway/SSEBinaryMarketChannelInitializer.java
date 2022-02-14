package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.config.SSEExchangeConfigure;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class SSEBinaryMarketChannelInitializer extends ChannelInitializer {

    private final SSEBinaryMarketHandler marketHandler;

    private final SSEExchangeConfigure configure;

    public SSEBinaryMarketChannelInitializer(SSEBinaryMarketHandler marketHandler, SSEExchangeConfigure configure) {
        this.marketHandler = marketHandler;
        this.configure = configure;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast(new SSEBinaryReadIdleStateHandler(configure.getHeartbeatInterval(), 0, 0))
                .addLast(new SSEBinaryHeartbeatLostHandler(configure.getHeartbeatInterval() * 2, 0, 0))
                .addLast(new SSEBinaryMarketProtocolDecoder())
                .addLast(new SSEBinaryMarketPakDecoder())
                .addLast(marketHandler)
                .addLast(new SSEBinaryMarketPakEncoder());
    }
}
