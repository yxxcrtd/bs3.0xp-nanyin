package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstIndexComponentKey extends BaseEntity {
    /**
     * 基准编号
     */
    private String indexId;

    /**
     * 行情代码
     */
    private String componentId;

    /**
     * 行情类型
     */
    private String componentType;

    /**
     * 取得 基准编号
     * @return 基准编号
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 基准编号
     * @param indexId 基准编号
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * 取得 行情代码
     * @return 行情代码
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * 设置 行情代码
     * @param componentId 行情代码
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId == null ? null : componentId.trim();
    }

    /**
     * 取得 行情类型
     * @return 行情类型
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * 设置 行情类型
     * @param componentType 行情类型
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType == null ? null : componentType.trim();
    }
}