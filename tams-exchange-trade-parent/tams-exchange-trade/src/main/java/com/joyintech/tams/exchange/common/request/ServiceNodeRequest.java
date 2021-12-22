package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author swwan
 * @date 2021/12/22 - 15:04
 */
@ApiModel(value = "添加、修改场内交易服务节点请求对象", description = "添加、修改场内交易服务节点请求对象")
public class ServiceNodeRequest extends BaseRequest {

    @ApiModelProperty(required = true, name = "nodeName", value = "服务节点名称", example = "terminal-01")
    private String nodeName;

    @ApiModelProperty(required = true, name = "nodeAddress", value = "服务节点IP:PORT", example = "127.0.0.1:9190")
    private String nodeAddress;

    @ApiModelProperty(required = true, name = "nodeWeight", value = "权重", example = "10")
    private int nodeWeight;

    @ApiModelProperty(required = true, name = "nodeType", value = "节点类型 交易；终端", example = "01")
    private String nodeType;

    @ApiModelProperty(required = true, name = "masterNode", value = "是否主节点", example = "0")
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

    public int getMasterNode() {
        return masterNode;
    }

    public void setMasterNode(int masterNode) {
        this.masterNode = masterNode;
    }
}