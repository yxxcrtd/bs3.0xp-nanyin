package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBatchTaskGrp extends BaseEntity {
    /**
     * null
     */
    private String taskGrpId;

    /**
     * null
     */
    private String taskGrpName;

    /**
     * null
     */
    private String taskGrpType;

    /**
     * null
     */
    private String pTaskGrpId;

    /**
     * null
     */
    private String taskId;

    /**
     * null
     */
    private Long orderNo;

    /**
     * 取得 null
     * @return null
     */
    public String getTaskGrpId() {
        return taskGrpId;
    }

    /**
     * 设置 null
     * @param taskGrpId null
     */
    public void setTaskGrpId(String taskGrpId) {
        this.taskGrpId = taskGrpId == null ? null : taskGrpId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTaskGrpName() {
        return taskGrpName;
    }

    /**
     * 设置 null
     * @param taskGrpName null
     */
    public void setTaskGrpName(String taskGrpName) {
        this.taskGrpName = taskGrpName == null ? null : taskGrpName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTaskGrpType() {
        return taskGrpType;
    }

    /**
     * 设置 null
     * @param taskGrpType null
     */
    public void setTaskGrpType(String taskGrpType) {
        this.taskGrpType = taskGrpType == null ? null : taskGrpType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getpTaskGrpId() {
        return pTaskGrpId;
    }

    /**
     * 设置 null
     * @param pTaskGrpId null
     */
    public void setpTaskGrpId(String pTaskGrpId) {
        this.pTaskGrpId = pTaskGrpId == null ? null : pTaskGrpId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 null
     * @param taskId null
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 null
     * @param orderNo null
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
}