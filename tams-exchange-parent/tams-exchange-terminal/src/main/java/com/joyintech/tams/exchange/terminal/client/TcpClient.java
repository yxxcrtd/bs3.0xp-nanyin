package com.joyintech.tams.exchange.terminal.client;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.common.utils.HttpUtils;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.common.utils.SpringContextUtils;
import com.netflix.appinfo.InstanceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class TcpClient implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TcpClient.class);

    @Resource(name = "serverBootstrap")
    private Bootstrap bootstrap;

    @Resource(name = "serverAddresses")
    private InetSocketAddress serverAddress;

    @Value("${netty.reconnect.delay}")
    private int delay;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${trade.serverName}")
    private String serverName;

    @Value("${agent.servers}")
    private String server;

    @Value("${netty.port}")
    private int nettyPort;

    @Resource
    private ApplicationContext ctx;

    private volatile boolean startFlag = true;

    private int time = 0;

    private ChannelFuture channelFuture;

    private void connect(InetSocketAddress inetSocketAddress) {
        channelFuture = bootstrap.connect(inetSocketAddress);
        channelFuture.addListener((ChannelFuture future) -> {
            if (future.isSuccess()) {
                logger.info("connect server: {}:{} success", inetSocketAddress.getHostString(), inetSocketAddress.getPort());
            } else {
                logger.info("connect server: {}:{} failed", inetSocketAddress.getHostString(), inetSocketAddress.getPort());
                future.channel().eventLoop().schedule(this::start,
                        delay, TimeUnit.SECONDS);
            }
        });
    }

    public void start() {
        connect(serverAddress);
    }

    @PreDestroy
    public void stop() throws Exception {
        bootstrap.group().shutdownGracefully();
        channelFuture.channel().closeFuture().sync();
    }


    public void reStart() {
        findTradeMasterAddress();
        reConnect(serverAddress);
    }

    private void findTradeMasterAddress() {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
            if (Objects.nonNull(instances)) {
                logger.info("eureka上注册服务信息 serverName {} -- {}", serverName, JsonUtils.getJsonFromObject(instances));
                for (ServiceInstance serviceInstance : instances) {
                    EurekaDiscoveryClient.EurekaServiceInstance eurekaServiceInstance = (EurekaDiscoveryClient.EurekaServiceInstance) serviceInstance;
                    InstanceInfo instanceInfo = eurekaServiceInstance.getInstanceInfo();
                    if (InstanceInfo.InstanceStatus.UP.name().equals(instanceInfo.getStatus().name())) {
                        String url = serviceInstance.getUri() + TradeServiceURL.prefix + TradeServiceURL.queryMaster;
                        ResultBean resultBean;
                        try {
                            String result = HttpUtils.doRequest(url, "GET", null);
                            resultBean = JsonUtils.getObjectFromJson(result, ResultBean.class);
                        } catch (Exception e) {
                            logger.warn("获取主节点失败，正在重试 ... {}", e.getMessage());
                            continue;
                        }
                        if (Objects.nonNull(resultBean) && resultBean.getErrorCode() == ErrorCode.SUCCESS.value) {
                            if (Objects.nonNull(resultBean.getData())) {
                                server = (String) resultBean.getData();
                                freshServerAddress(server);
                                break;
                            } else {
                                logger.info("masterAddress is null");
                            }
                        } else {
                            logger.info("主节点信息查询失败 {} -- {}", resultBean.getErrorCode(), resultBean.getErrorMsg());
                        }
                    }
                }
            } else {
                logger.info("无服务注册，尝试连接老节点 {} ...", server);
            }
        } catch (Exception e) {
            logger.error("主节点查询错误，{}", e);
        }
    }

    private void reConnect(InetSocketAddress inetSocketAddress) {
        channelFuture = bootstrap.connect(inetSocketAddress);
        channelFuture.addListener((ChannelFuture future) -> {
            if (future.isSuccess()) {
                logger.info("reConnect server: {}:{} success", inetSocketAddress.getHostString(), inetSocketAddress.getPort());
            } else {
                logger.info("reConnect server: {}:{} failed", inetSocketAddress.getHostString(), inetSocketAddress.getPort());
                future.channel().eventLoop().schedule(this::reStart,
                        delay, TimeUnit.SECONDS);
            }
            if (delay < 30)
                delay = delay + 1;
        });
    }

    public void freshServerAddress(String server) {
        String[] strings = server.split(":");
        serverAddress = new InetSocketAddress(strings[0], nettyPort);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (startFlag) {
                startFlag = false;
                SpringContextUtils.setApplicationContext(ctx);
                start();
            }
        } catch (Exception e) {
            logger.error("TcpClient start error {}", e);
        }
    }
}
