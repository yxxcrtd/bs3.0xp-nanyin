package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;

public class ServiceNode implements Serializable {
    private String nodeName;

    private String nodeAddress;

    private int nodeWeight;

    private String nodeType;

    private long lastLiveTime;

    private int masterNode;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(String nodeAddress) {
        this.nodeAddress = nodeAddress == null ? null : nodeAddress.trim();
    }

    public int getNodeWeight() {
        return nodeWeight;
    }

    public void setNodeWeight(int nodeWeight) {
        this.nodeWeight = nodeWeight;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    public long getLastLiveTime() {
        return lastLiveTime;
    }

    public void setLastLiveTime(long lastLiveTime) {
        this.lastLiveTime = lastLiveTime;
    }

    public int getMasterNode() {
        return masterNode;
    }

    public void setMasterNode(int masterNode) {
        this.masterNode = masterNode;
    }
}