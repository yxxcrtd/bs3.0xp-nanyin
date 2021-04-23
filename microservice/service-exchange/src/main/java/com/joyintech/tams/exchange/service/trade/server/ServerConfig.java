package com.joyintech.tams.exchange.service.trade.server;

import com.joyintech.tams.exchange.common.net.handler.CXHeartbeatHandler;
import com.joyintech.tams.exchange.common.net.handler.CXServerDecoder;
import com.joyintech.tams.exchange.common.net.handler.CXServerEncoder;
import com.joyintech.tams.exchange.common.net.handler.CXServerHandler;
import com.joyintech.tams.exchange.service.trade.server.agent.TradeAgent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class ServerConfig {

    @Value("${netty.thread.num.boss}")
    private Integer bossNum;

    @Value("${netty.thread.num.worker}")
    private Integer workerNum;

    @Value("${netty.port}")
    private Integer tcpPort;

    @Value("${netty.idle.read}")
    private Integer readIdleSecond;

    @Value("${netty.idle.write}")
    private Integer writeIdleSecond;

    @Value("${netty.idle.all}")
    private Integer allIdleSecond;

    @Value("${netty.buffer.recv}")
    private Integer nettyRecvBufferSize;

    @Value("${netty.buffer.send}")
    private Integer nettySendBufferSize ;

    @Value("${netty.buffer.write.high}")
    private Integer nettyWriteBufferHighKB ;

    @Value("${netty.buffer.write.low}")
    private Integer nettyWriteBufferLowKB;

    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {

        return new ServerBootstrap()
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_RCVBUF, nettyRecvBufferSize)
                .option(ChannelOption.SO_SNDBUF, nettySendBufferSize)
                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, nettyWriteBufferHighKB * 1024)
                .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, nettyWriteBufferLowKB * 1024)
                .childHandler(new ChannelInitializer(){
                    @Override
                    protected void initChannel(Channel channel){
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("timeout", new IdleStateHandler(readIdleSecond, writeIdleSecond, allIdleSecond))
                                .addLast("heartbeat", new CXHeartbeatHandler())
                                .addLast("encode", new CXServerEncoder())
                                .addLast("decode", new CXServerDecoder())
                                .addLast("handler", new CXServerHandler(TradeAgent.class));
                        }
                });
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossNum);
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerNum);
    }

    @Bean(name = "tcpPort")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(tcpPort);
    }

}
