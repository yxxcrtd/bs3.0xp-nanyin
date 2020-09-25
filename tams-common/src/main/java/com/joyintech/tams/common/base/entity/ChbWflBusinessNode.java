package com.joyintech.tams.common.base.entity;

public class ChbWflBusinessNode extends ChbWflBusinessNodeKey {
    /**
     * 流程实例代码
     */
    private String workflowId;

    /**
     * 业务流水号
     */
    private String businessId;

    /**
     * 处理人
     */
    private String hiUser;

    /**
     * 节点代码
     */
    private String nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 处理结果
     */
    private String dismount;

    /**
     * 处理意见
     */
    private String character;

    /**
     * 取得 流程实例代码
     * @return 流程实例代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 流程实例代码
     * @param workflowId 流程实例代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 业务流水号
     * @return 业务流水号
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * 设置 业务流水号
     * @param businessId 业务流水号
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    /**
     * 取得 处理人
     * @return 处理人
     */
    public String getHiUser() {
        return hiUser;
    }

    /**
     * 设置 处理人
     * @param hiUser 处理人
     */
    public void setHiUser(String hiUser) {
        this.hiUser = hiUser == null ? null : hiUser.trim();
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
     * 取得 节点名称
     * @return 节点名称
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置 节点名称
     * @param nodeName 节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * 取得 处理结果
     * @return 处理结果
     */
    public String getDismount() {
        return dismount;
    }

    /**
     * 设置 处理结果
     * @param dismount 处理结果
     */
    public void setDismount(String dismount) {
        this.dismount = dismount == null ? null : dismount.trim();
    }

    /**
     * 取得 处理意见
     * @return 处理意见
     */
    public String getCharacter() {
        return character;
    }

    /**
     * 设置 处理意见
     * @param character 处理意见
     */
    public void setCharacter(String character) {
        this.character = character == null ? null : character.trim();
    }
}