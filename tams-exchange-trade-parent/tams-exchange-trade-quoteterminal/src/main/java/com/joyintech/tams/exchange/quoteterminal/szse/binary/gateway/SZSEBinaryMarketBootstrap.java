package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway;

import com.joyintech.tams.exchange.quoteterminal.szse.binary.config.SZExchangeConfigure;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline.SZSEBinaryMarketHandler;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.Message;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.SZSEBinaryHeartbeat;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.SZSEBinaryLogon;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.SZSEBinaryLogout;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class SZSEBinaryMarketBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryMarketBootstrap.class);

    private static volatile SZSEBinaryMarketBootstrap INSTANCE;

    private Bootstrap bootstrap;

    private Channel channel;

    private SZExchangeConfigure config;

    private SZSEBinaryMessageFacade facade;

    private InetSocketAddress inetSocketAddress;

    private ConnectionListener connectionListener;

    private SZSEBinaryHeartbeat heartbeat = new SZSEBinaryHeartbeat();

    /**
     * 本次重连的数量累计
     */
    private int reconnectCount = 1;

    public static SZSEBinaryMarketBootstrap getInstance(SZExchangeConfigure config, SZSEBinaryMessageFacade SZSEBinaryMessageFacade) {
        if (INSTANCE == null) {
            synchronized (SZSEBinaryMarketBootstrap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SZSEBinaryMarketBootstrap(config, SZSEBinaryMessageFacade);
                }
            }
        }
        return INSTANCE;
    }

    private SZSEBinaryMarketBootstrap(SZExchangeConfigure config, SZSEBinaryMessageFacade SZSEBinaryMessageFacade) {
        this.config = config;
        this.facade = SZSEBinaryMessageFacade;
    }

    public void init() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(5);
        this.bootstrap = new Bootstrap();
        this.bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getTimeout())
                .option(ChannelOption.SO_RCVBUF, config.getNettyRecvBufferSize())
                .option(ChannelOption.SO_SNDBUF, config.getNettySendBufferSize())
                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, config.getNettyWriteBufferHighKB() * 1024)
                .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, config.getNettyWriteBufferLowKB() * 1024)
                .handler(new SZSEBinaryMarketChannelInitializer(new SZSEBinaryMarketHandler(facade), config));
        this.inetSocketAddress = new InetSocketAddress(config.getIp(), config.getPort());
        this.connectionListener = new ConnectionListener(config, this);
    }


    public void connect() {
        bootstrap.connect(inetSocketAddress).addListener(connectionListener);
    }

    /**
     * 重连
     *
     * @param exitSystem 重连到达指定次数后，依旧没有连上，是否退出系统
     * @param logError   是否打印重连过程中的错误信息
     */
    public synchronized void reconnect(boolean exitSystem, boolean logError) {
        disconnect();
        int reconnect = config.getReconnect();
        if(config.getReconnect() == 0) {
            logger.info("initReconnect is 0");
            reconnect = Integer.MAX_VALUE;
        }
        while(1 <= reconnect) {
            try {
                logger.info("第{}次开始重连...", reconnectCount);
                ChannelFuture sync = bootstrap.connect(config.getIp(), config.getPort()).sync();
                if(sync.isSuccess()) {
                    channel = sync.channel();
                    this.reconnectCount = 1;
                    logger.info("connect to server success -> {}:{}", config.getIp(), config.getPort());
                    return;
                }
                TimeUnit.SECONDS.sleep(config.getReconnectInterval());
            } catch (Exception e) {
                if(logError) {
                    logger.error("initReconnect failure: ", e);
                }
                try {
                    TimeUnit.SECONDS.sleep(config.getReconnectInterval());
                } catch (InterruptedException e1) {
                    if(logError) {
                        logger.error("reconnect sleep error: ", e1);
                    }
                }
            }
            reconnectCount++;
            reconnect--;
        }
        if(!isConnect() && exitSystem) {
            logger.error("重连失败，退出系统.");
            System.exit(10);
        }
    }

    public void login() {
        SZSEBinaryLogon logon = new SZSEBinaryLogon(config.getPassword(), config.getSenderCompId()
                , config.getTargetCompId(), config.getHeartbeatInterval(), config.getVersion());
        sendMessage(logon);
        logger.info("send login to server {}:{}", config.getIp(), config.getPort());
    }

    /**
     * 此处为客户端主动退出行情终端。 故做此操作时，不必进行断线重连操作
     */

    public void logout() throws Exception {
        SZSEBinaryLogout logout = new SZSEBinaryLogout();
        sendMessage(logout);
        logger.info("send logout to server {}:{}", config.getIp(), config.getPort());
        channel.closeFuture().sync();
        bootstrap.group().shutdownGracefully();
    }

    public void sendMessage(Message message) {
        logger.info("send message: [{}]", message.toString());
        if (isConnect()) {
            channel.writeAndFlush(message);
        }
    }

    /**
     * 尝试重连到连上了为止
     */
    public void keepReconnect() {
        disconnect();
        if(channel != null && channel.isActive()) {
            return;
        }
        logger.info("后台持续重连中 -> {}:{}", config.getIp(), config.getPort());
        while(channel == null || !channel.isActive()) {
            reconnect(false, false);
        }
    }

    public synchronized void disconnect() {
        if (channel != null && channel.isActive()) {
            channel.close();
        }
    }

    private boolean isConnect() {
        return channel != null && channel.isOpen() && channel.isActive();
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void sendHeartbeat() {
        if (!isConnect()) {
            keepReconnect();
        } else {
            sendMessage(heartbeat);
            this.channel.eventLoop().schedule(this::sendHeartbeat,
                    config.getHeartbeatInterval(), TimeUnit.SECONDS);
        }
    }
}
