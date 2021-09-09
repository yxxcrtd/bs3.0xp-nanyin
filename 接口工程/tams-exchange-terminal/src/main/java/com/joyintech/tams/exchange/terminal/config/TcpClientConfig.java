package com.joyintech.tams.exchange.terminal.config;



import com.joyintech.tams.exchange.common.net.handler.CXHeartbeatHandler;
import com.joyintech.tams.exchange.common.net.handler.CXServerDecoder;
import com.joyintech.tams.exchange.common.net.handler.CXServerEncoder;
import com.joyintech.tams.exchange.common.net.handler.CXServerHandler;
import com.joyintech.tams.exchange.terminal.agent.TerminalAgent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class TcpClientConfig {

    @Value("${agent.servers}")
    private String servers;

    @Value("${netty.thread.num.worker:2}")
    private int workerNum;

    @Value("${netty.timeout:10000}")
    private int timeout;

    @Value("${netty.idle.read:20}")
    private int readIdleSecond;

    @Value("${netty.idle.write:10}")
    private int writeIdleSecond;

    @Value("${netty.idle.all:0}")
    private int allIdleSecond;

    @Value("${netty.buffer.recv:4194304}")
    private int nettyRecvBufferSize;

    @Value("${netty.buffer.send:4194304}")
    private int nettySendBufferSize;

    @Value("${netty.buffer.write.high:524288}")
    private int nettyWriteBufferHighKB;

    @Value("${netty.buffer.write.low:32768}")
    private int nettyWriteBufferLowKB;

    @Bean(name = "serverBootstrap")
    public Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .option(ChannelOption.SO_RCVBUF, nettyRecvBufferSize)
                .option(ChannelOption.SO_SNDBUF, nettySendBufferSize)
                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, nettyWriteBufferHighKB * 1024)
                .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, nettyWriteBufferLowKB * 1024)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("timeout", new IdleStateHandler(readIdleSecond, writeIdleSecond, allIdleSecond))
                                .addLast("heartbeat", new CXHeartbeatHandler())
                                .addLast("encode", new CXServerEncoder())
                                .addLast("decode", new CXServerDecoder())
                                .addLast("handler", new CXServerHandler(TerminalAgent.class));
                    }
                });
        return bootstrap.group(new NioEventLoopGroup(workerNum));
    }

    @Bean(name = "serverAddresses")
    public InetSocketAddress serverAddress() {
        String[] strings = servers.split(":");
        return new InetSocketAddress(strings[0], Integer.parseInt(strings[1]));
    }

}
