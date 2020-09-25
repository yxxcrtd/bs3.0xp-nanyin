package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbWflModule extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 模板代码
     */
    private String moduleId;

    /**
     * 节点代码
     */
    private String nodeId;

    /**
     * 角色代码
     */
    private String valueType;

    /**
     * 用户代码
     */
    private String value;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置 模板代码
     * @param moduleId 模板代码
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
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
     * 取得 角色代码
     * @return 角色代码
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 设置 角色代码
     * @param valueType 角色代码
     */
    public void setValueType(String valueType) {
        this.valueType = valueType == null ? null : valueType.trim();
    }

    /**
     * 取得 用户代码
     * @return 用户代码
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 用户代码
     * @param value 用户代码
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}