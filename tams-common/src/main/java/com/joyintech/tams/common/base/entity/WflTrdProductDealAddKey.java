package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflTrdProductDealAddKey extends BaseEntity {
    /**
     * ????
     */
    private String tradeId;

    /**
     * ????
     */
    private String workflowStatus;

    /**
     * 取得 ????
     * @return ????
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 ????
     * @param tradeId ????
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 ????
     * @param workflowStatus ????
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }
}