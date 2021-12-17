package com.joyintech.tams.exchange.common.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 15:30 2021/8/16
 */
@Component
@Configuration
public class QuoteServiceNodeConfig {
    @Value("${quote.enable:false}")
    private boolean enable;
    /**
     * 行情节点名称
     */
    @Value("${quote.node.name:tong_shen}")
    private String serviceNodeName;
    /**
     * 节点权重
     */
    @Value("${quote.node.weight:1}")
    private int serviceWeight;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getServiceNodeName() {
        return serviceNodeName;
    }

    public void setServiceNodeName(String serviceNodeName) {
        this.serviceNodeName = serviceNodeName;
    }

    public int getServiceWeight() {
        return serviceWeight;
    }

    public void setServiceWeight(int serviceWeight) {
        this.serviceWeight = serviceWeight;
    }
}
