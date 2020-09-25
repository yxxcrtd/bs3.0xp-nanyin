package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflModule extends BaseEntity {
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
     * 数据类型
     */
    private String valueType;

    /**
     * 数据值
     */
    private String valueData;

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
     * 取得 数据类型
     * @return 数据类型
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 设置 数据类型
     * @param valueType 数据类型
     */
    public void setValueType(String valueType) {
        this.valueType = valueType == null ? null : valueType.trim();
    }

    /**
     * 取得 数据值
     * @return 数据值
     */
    public String getValueData() {
        return valueData;
    }

    /**
     * 设置 数据值
     * @param valueData 数据值
     */
    public void setValueData(String valueData) {
        this.valueData = valueData == null ? null : valueData.trim();
    }
}