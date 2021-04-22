package com.joyintech.tams.exchange.common.db.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class Broker implements Serializable, KeyMethodInterface<String> {
    private String id;

    private String brokerId;

    private String brokerName;

    private String servers;

    private Date createTime;

    private Date updateTime;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId == null ? null : brokerId.trim();
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName == null ? null : brokerName.trim();
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers == null ? null : servers.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}