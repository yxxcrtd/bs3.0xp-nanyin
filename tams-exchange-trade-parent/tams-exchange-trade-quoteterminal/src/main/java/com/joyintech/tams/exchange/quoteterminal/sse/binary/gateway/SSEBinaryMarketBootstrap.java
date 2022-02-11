package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.config.SSEExchangeConfigure;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline.SSEBinaryMarketHandler;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.Message;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.SSEBinaryLogon;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.SSEBinaryLogout;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SSEBinaryMarketBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(SSEBinaryMarketBootstrap.class);

    private static volatile SSEBinaryMarketBootstrap INSTANCE;
    private final SSEExchangeConfigure config;
    private final SSEBinaryMessageFacade facade;
    private volatile boolean running;
    private Bootstrap bootstrap;
    private Channel channel;
    /**
     * 本次重连的数量累计
     */
    private int reconnectCount = 1;
    /**
     * 出向消息序号
     */
    public static AtomicLong nxtOut = new AtomicLong(1L);

    /**
     * 登陆时置为初始值 1L
     */
    private void clearNxtOut() {
        nxtOut.set(1L);
    }

    private SSEBinaryMarketBootstrap(SSEExchangeConfigure config, SSEBinaryMessageFacade SSEBinaryMessageFacade) {
        this.config = config;
        this.facade = SSEBinaryMessageFacade;
    }

    public static SSEBinaryMarketBootstrap getInstance(SSEExchangeConfigure config, SSEBinaryMessageFacade SSEBinaryMessageFacade) {
        if (INSTANCE == null) {
            synchronized (SSEBinaryMarketBootstrap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SSEBinaryMarketBootstrap(config, SSEBinaryMessageFacade);
                }
            }
        }
        return INSTANCE;
    }

    public boolean connect() {
        if (!running) {
            NioEventLoopGroup workerGroup = new NioEventLoopGroup(5);
            this.bootstrap = new Bootstrap();
            this.bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new SSEBinaryMarketChannelInitializer(new SSEBinaryMarketHandler(facade), config));
            try {
                ChannelFuture future = bootstrap.connect(config.getIp(), config.getPort()).sync();
                if (future.isSuccess()) {
                    channel = future.channel();
                    running = true;
                    logger.info("connect to server success -> {}:{}", config.getIp(), config.getPort());
                } else {
                    logger.info("connect to server failure -> {}:{}", config.getIp(), config.getPort());
                    reconnect(true, true);
                }
            } catch (InterruptedException e) {
                logger.error("connect to server error: ", e);
                reconnect(true, true);
            }
        }
        return running;
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
        if (config.getReconnect() == 0) {
            logger.info("initReconnect is 0");
            reconnect = Integer.MAX_VALUE;
        }
        while (1 <= reconnect) {
            try {
                logger.info("第{}次开始重连...", reconnectCount);
                ChannelFuture sync = bootstrap.connect(config.getIp(), config.getPort()).sync();
                if (sync.isSuccess()) {
                    channel = sync.channel();
                    this.running = true;
                    this.reconnectCount = 1;
                    logger.info("connect to server success -> {}:{}", config.getIp(), config.getPort());
                    return;
                }
                TimeUnit.SECONDS.sleep(config.getReconnectInterval());
            } catch (Exception e) {
                if (logError) {
                    logger.error("initReconnect failure: ", e);
                }
                try {
                    TimeUnit.SECONDS.sleep(config.getReconnectInterval());
                } catch (InterruptedException e1) {
                    if (logError) {
                        logger.error("reconnect sleep error: ", e1);
                    }
                }
            }
            reconnectCount++;
            reconnect--;
        }
        if (!isConnect() && exitSystem) {
            logger.error("重连失败，退出系统.");
            System.exit(10);
        }
    }

    public void login() {
        clearNxtOut();
        SSEBinaryLogon logon = new SSEBinaryLogon(config.getSenderCompId()
                , config.getTargetCompId(), config.getHeartbeatInterval(), config.getVersion());
        sendMessage(logon);
        logger.info("send login to server {}:{}", config.getIp(), config.getPort());
    }

    public void logout() {
        SSEBinaryLogout logout = new SSEBinaryLogout();
        sendMessage(logout);
        logger.info("send logout to server {}:{}", config.getIp(), config.getPort());
        channel.close();
    }

    public void sendMessage(Message message) {
        logger.debug("send message: [{}]", message.toString());
        message.setMsgSeqNum(nxtOut.getAndIncrement());
        if (isConnect()) {
            channel.writeAndFlush(message);
        }
    }

    /**
     * 尝试重连到连上了为止
     */
    public void keepReconnect() {
        disconnect();
        if (channel != null && channel.isActive()) {
            return;
        }
        logger.info("后台持续重连中 -> {}:{}", config.getIp(), config.getPort());
        while (channel == null || !channel.isActive()) {
            reconnect(false, false);
        }
    }

    public synchronized void disconnect() {
        if (channel != null && channel.isActive()) {
            channel.close();
        }
    }

    private boolean isConnect() {
        return running && channel != null && channel.isOpen() && channel.isActive();
    }
}
