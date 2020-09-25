package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflStlPayOrdersKey extends BaseEntity {
    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 数据状态
     */
    private String workflowStatus;

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 指令编号
     * @param orderId 指令编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 数据状态
     * @param workflowStatus 数据状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }
}