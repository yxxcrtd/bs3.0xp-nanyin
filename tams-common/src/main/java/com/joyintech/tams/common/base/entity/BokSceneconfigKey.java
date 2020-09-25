package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokSceneconfigKey extends BaseEntity {
    /**
     * 场景分支节点
     */
    private String sceneNodeId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 节点代码
     */
    private String nodeId;

    /**
     * 节点内容
     */
    private String nodeValue;

    /**
     * 取得 场景分支节点
     * @return 场景分支节点
     */
    public String getSceneNodeId() {
        return sceneNodeId;
    }

    /**
     * 设置 场景分支节点
     * @param sceneNodeId 场景分支节点
     */
    public void setSceneNodeId(String sceneNodeId) {
        this.sceneNodeId = sceneNodeId == null ? null : sceneNodeId.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 节点代码
     * @return 节点代码
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置 节点代码
     * @param nodeId 节点代码
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    /**
     * 取得 节点内容
     * @return 节点内容
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * 设置 节点内容
     * @param nodeValue 节点内容
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue == null ? null : nodeValue.trim();
    }
}