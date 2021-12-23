package com.joyintech.tams.exchange.common.db.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "场内交易服务节点信息表", description = "场内交易服务节点信息表")
public class ServiceNode implements Serializable {

    private static final long serialVersionUID = -3838835139835245630L;


    @ApiModelProperty(value = "服务节点名称")
    private String nodeName;


    @ApiModelProperty(value = "服务节点IP:PORT")
    private String nodeAddress;


    @ApiModelProperty(value = "权重")
    private int nodeWeight;


    @ApiModelProperty(value = "节点类型 交易；终端")
    private String nodeType;


    @ApiModelProperty(value = "最后更新时间")
    private long lastLiveTime;


    @ApiModelProperty(value = "是否主节点")
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