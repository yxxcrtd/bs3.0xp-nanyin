package com.joyintech.tams.exchange.terminal.trade.jsd;

import com.joyintech.tams.exchange.terminal.constant.AccountLoginStatus;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MessageManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * create By MAXX on 2020/6/19
 */

public class JSDClient {
    private static final Logger logger = LoggerFactory.getLogger(JSDClient.class);

    private ChannelFuture channelFuture;

    private Bootstrap jsdBootstrap;

    private EventLoopGroup jsdWorkerGroup;

    private JSDTradeApi tradeApi;

    private MessageManager manager;

    private int delay;

    private String ip;

    private Integer port;

    public void init(String ip, int port, int delay, JSDTradeApi jsdTradeApi, MessageManager messageManager) {
        this.ip = ip;
        this.port = port;
        this.delay = delay;
        this.tradeApi = jsdTradeApi;
        this.manager = messageManager;

        jsdBootstrap = new Bootstrap();
        jsdBootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 512 * 1024 * 1024)
                .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 32 * 1024 * 1024)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().
                                addLast(new StringEncoder()).
                                addLast(new MessageDecoder(manager, tradeApi)).
                                addLast(new MessageHandler(tradeApi));
                    }
                });
        jsdWorkerGroup = new NioEventLoopGroup();
    }


    public void connect() {
        try {
            channelFuture = jsdBootstrap.group(jsdWorkerGroup).remoteAddress(ip, port).connect().sync();
            if (!channelFuture.isSuccess()) {
                logger.warn("connect jsd server failed");
            } else {
                logger.info("connect jsd server success");
                tradeApi.setChannel(channelFuture.channel());
            }
        } catch (Exception e) {
            logger.warn("init jsd exception:{}", e);
        }

    }

    public void reconnectDelay(){
        channelFuture.channel().eventLoop().schedule(this::reconnect,
                delay, TimeUnit.SECONDS);
        if (delay < 30)
            delay = delay + 1;
    }

    public void reconnect() {
        try {
            channelFuture = jsdBootstrap.connect().sync();
            if (!channelFuture.isSuccess()) {
                logger.warn("重新连接jsd失败,正在尝试重新连接...");
                reconnectDelay();
            } else {
                logger.info("jsd重连成功,开始重新登录账户...");
                tradeApi.setChannel(channelFuture.channel());
                tradeApi.getJsdTradeClient().getAccounts().forEach((financeAccount, account) -> {
                    tradeApi.reqClientLogin(account);
                    tradeApi.setStatus(account.getId(), AccountLoginStatus.TRADING);
                });
            }
        } catch (Exception e) {
            reconnectDelay();
            logger.warn("重新连接jsd失败：exception:{}", e);
        }
    }

    @PreDestroy
    public void stop() throws Exception {

        jsdBootstrap.group().shutdownGracefully();
        channelFuture.channel().closeFuture().sync();
    }

    public void release() {
        try {
            tradeApi.getChannel().close().sync();
            jsdWorkerGroup.shutdownGracefully().sync();
        } catch (Exception e) {
            logger.warn("release jsd exception:{}", e);
        }
    }
}
