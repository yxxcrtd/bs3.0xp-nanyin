package com.joyintech.tams.exchange.service.trade.scheduled;


import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.cache.config.DataServiceConfig;
import com.joyintech.tams.exchange.common.cache.impl.RedisCacheServiceImpl;
import com.joyintech.tams.exchange.common.db.domain.ServiceNode;
import com.joyintech.tams.exchange.common.microservice.MasterNodeStatus;
import com.joyintech.tams.exchange.common.microservice.ServiceNodeType;
import com.joyintech.tams.exchange.common.utils.HttpUtils;
import com.joyintech.tams.exchange.common.utils.SpringContextUtils;
import com.joyintech.tams.exchange.datacommon.db.service.ServiceNodeService;
import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class TokenJob {
    private static final Logger logger = LoggerFactory.getLogger(TokenJob.class);

    //主从标志，备机只接收与处理消息，不推送任何消息
    public static boolean isMaster = false;
    public static String masterAddress = null;
    @Value("${scheduled.token.node}")
    private String node;
    @Value("${scheduled.token.enable}")
    public boolean enableToken;

    @Value("${spring.application.name}")
    private String serviceName;
    @Value("${eureka.instance.instance-id}")
    private String instanceName;
    @Value("${eureka.instance.hostname}")
    private String hostName;
    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String eurekaUrl;

    @Value("${scheduled.token.fixedRate}")
    private long fixedRate;
    @Autowired
    private ServiceNodeService serviceNodeService;

    @Autowired
    private CommonManagement commonManagement;

    @Autowired
    private DataServiceConfig dataServiceConfig;

    private boolean isOutOfService = true;// 是否需要掉线服务

    @PostConstruct
    public void processUrl() {
        if (Objects.nonNull(eurekaUrl)) {
            eurekaUrl = eurekaUrl + "apps/" + serviceName + "/"
                    + (Strings.isNullOrEmpty(instanceName) ? hostName : instanceName)
                    + "/status?value=";
        }
        logger.info("eureka url:{}", eurekaUrl);
    }

    @Scheduled(fixedRateString = "${scheduled.token.fixedRate}")
    public void tokenCheck() {
        try {
            if (!enableToken) {
                isMaster = true;
                return;
            }
            ServiceNode thisNode = commonManagement.queryServiceNode(node).get(0);
            if (Objects.isNull(thisNode)) {
                logger.info("can not found the ServiceNode by yml node, please check the node !!!!");
                return;
            }
            if (isMaster) {
                thisNode.setLastLiveTime(System.currentTimeMillis());
                serviceNodeService.updateServiceNode(thisNode);
                return;
            }
            logger.info("check master...");
            List<ServiceNode> nodeList = commonManagement.queryServiceNode(null).stream()
                    .filter(serviceNode -> ServiceNodeType.TRADE_SERVICE.equals(serviceNode.getNodeType()))
                    .sorted(Comparator.comparingInt(ServiceNode::getNodeWeight).reversed()).collect(Collectors.toList());
            long currentTime = System.currentTimeMillis();
            for (ServiceNode serviceNode : nodeList) {
                if (node.equals(serviceNode.getNodeName())) {
                    //有其他节点是master，或者更高权重节点在线时，不争抢master
                    List<ServiceNode> serviceNodeLive = nodeList.stream()
                            .filter(serviceNode1 ->
                                    (serviceNode1.getMasterNode() == MasterNodeStatus.MASTER || serviceNode1.getNodeWeight() > serviceNode.getNodeWeight())
                                            && currentTime - serviceNode1.getLastLiveTime() < fixedRate * 2)
                            .collect(Collectors.toList());
                    if (serviceNodeLive.size() == 0) {
                        logger.info("cover cache from redis...");
                        Map<String, RedisCacheServiceImpl> redisCacheServiceMap = SpringContextUtils.getApplicationContext().getBeansOfType(RedisCacheServiceImpl.class);
                        redisCacheServiceMap.values().forEach(service -> service.coverCache(dataServiceConfig.isUseRedis()));
                        logger.info("cover cache from redis success!");
                        serviceNode.setLastLiveTime(System.currentTimeMillis());
                        serviceNode.setMasterNode(MasterNodeStatus.MASTER);
                        isMaster = true;
                        serviceNodeService.updateServiceNode(serviceNode);
                        if (changeEurekaClientStatus(InstanceInfo.InstanceStatus.UP.name())) {
                            masterAddress = serviceNode.getNodeAddress();
                            logger.info("current node {} is master now!!!", masterAddress);
                            return;
                        } else {
                            isMaster = false;
                            serviceNode.setMasterNode(MasterNodeStatus.SLAVER);
                            serviceNodeService.updateServiceNode(serviceNode);
                            logger.info("register eureka failed,{} - {} abandon change to master", serviceName, instanceName);
                            continue;
                        }
                    }
                } else {
                    //寻找master并记录
                    if (serviceNode.getMasterNode() == MasterNodeStatus.MASTER && currentTime - serviceNode.getLastLiveTime() < fixedRate * 2) {
                        masterAddress = serviceNode.getNodeAddress();
                        logger.info("current master is {} - {}", serviceNode.getNodeName(), masterAddress);
                        //如果从master切换到slaver，更改注册状态
                        if (isOutOfService) {
                            if (!changeEurekaClientStatus(InstanceInfo.InstanceStatus.OUT_OF_SERVICE.name())) {
                                logger.info("unregister eureka failed. {} - {}", serviceName, instanceName);
                            }
                        }
                        //slaver依然更新自己的时间，防止切换时不按优先级切换
                        isMaster = false;
                        thisNode.setLastLiveTime(currentTime);
                        thisNode.setMasterNode(MasterNodeStatus.SLAVER);
                        serviceNodeService.updateServiceNode(thisNode);
                        return;
                    }
                }
            }
            //可能权重高的节点还没有切换为master，先刷新自己的时间，防止被权重低的节点插队
            thisNode.setLastLiveTime(currentTime);
            thisNode.setMasterNode(MasterNodeStatus.SLAVER);
            serviceNodeService.updateServiceNode(thisNode);
            logger.info("not found master, please check reason...");
        } catch (Exception e) {
            logger.info("token check error:{}", e);
        }
    }

    private boolean changeEurekaClientStatus(String instanceStatus) {
        try {
            // eureka 管理接口这里没有返回数据，只有一个空字符串，暂时无法识别
            String result = HttpUtils.doRequest(eurekaUrl + instanceStatus, "PUT", null);
            logger.info("changeEurekaClientStatus result:{}", result);
            if (InstanceInfo.InstanceStatus.OUT_OF_SERVICE.name().equals(instanceStatus)) {
                isOutOfService = !Objects.nonNull(result);
            }
            return Objects.nonNull(result);
        } catch (Exception e) {
            logger.info("changeEurekaClientStatus error: {} - {}", instanceStatus, e);
            if (InstanceInfo.InstanceStatus.OUT_OF_SERVICE.name().equals(instanceStatus)) {
                isOutOfService = true;
            }
            return false;
        }
    }
}
