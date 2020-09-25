package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflTrdFinProductDealTotalKey extends BaseEntity {
    /**
     * ??????
     */
    private String totleTradeId;

    /**
     * ????
     */
    private String workflowStatus;

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 ??????
     * @param totleTradeId ??????
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
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