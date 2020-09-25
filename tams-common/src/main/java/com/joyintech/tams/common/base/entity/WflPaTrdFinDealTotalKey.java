package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflPaTrdFinDealTotalKey extends BaseEntity {
    /**
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 数据状态
     */
    private String workflowStatus;

    /**
     * 取得 汇总交易编号
     * @return 汇总交易编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
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