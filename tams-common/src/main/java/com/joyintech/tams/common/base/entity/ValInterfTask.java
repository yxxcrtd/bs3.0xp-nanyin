package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValInterfTask extends BaseEntity {
    /**
     * 任务ID
     */
    private String taskId;

    /**
     * bean名称
     */
    private String beanName;

    /**
     * 批次名称
     */
    private String taskName;

    /**
     * 批次序号
     */
    private Short orderno;

    /**
     * S：发送；R：接收
     */
    private String rs;

    /**
     * 资管系统：ZG
     */
    private String systemid;

    /**
     * 取得 任务ID
     * @return 任务ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务ID
     * @param taskId 任务ID
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 bean名称
     * @return bean名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置 bean名称
     * @param beanName bean名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * 取得 批次名称
     * @return 批次名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置 批次名称
     * @param taskName 批次名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * 取得 批次序号
     * @return 批次序号
     */
    public Short getOrderno() {
        return orderno;
    }

    /**
     * 设置 批次序号
     * @param orderno 批次序号
     */
    public void setOrderno(Short orderno) {
        this.orderno = orderno;
    }

    /**
     * 取得 S：发送；R：接收
     * @return S：发送；R：接收
     */
    public String getRs() {
        return rs;
    }

    /**
     * 设置 S：发送；R：接收
     * @param rs S：发送；R：接收
     */
    public void setRs(String rs) {
        this.rs = rs == null ? null : rs.trim();
    }

    /**
     * 取得 资管系统：ZG
     * @return 资管系统：ZG
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * 设置 资管系统：ZG
     * @param systemid 资管系统：ZG
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }
}