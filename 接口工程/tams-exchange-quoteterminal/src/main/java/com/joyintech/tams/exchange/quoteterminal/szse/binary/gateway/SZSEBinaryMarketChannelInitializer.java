package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.config.SZExchangeConfigure;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class SZSEBinaryMarketChannelInitializer extends ChannelInitializer {

    private SZSEBinaryMarketHandler marketHandler;

    private SZExchangeConfigure configure;

    public SZSEBinaryMarketChannelInitializer(SZSEBinaryMarketHandler marketHandler, SZExchangeConfigure configure) {
        this.marketHandler = marketHandler;
        this.configure = configure;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast(new SZSEBinaryReadIdleStateHandler(configure.getHeartbeatInterval(), 0, 0))
                .addLast(new SZSEBinaryHeartbeatLostHandler(configure.getHeartbeatInterval() * 2, 0, 0))
                .addLast(new SZSEBinaryMarketProtocolDecoder())
                .addLast(new SZSEBinaryMarketPakDecoder())
                .addLast(marketHandler)
                .addLast(new SZSEBinaryMarketPakEncoder());
    }
}
