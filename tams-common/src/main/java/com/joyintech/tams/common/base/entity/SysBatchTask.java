package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SysBatchTask extends BaseEntity {
    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 系统代码
     */
    private String systemCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 父任务代码
     */
    private String ftaskId;

    /**
     * 批处理业务名称
     */
    private String beanName;

    /**
     * 任务优先级
     */
    private Long taskPri;

    /**
     * 是否批处理启动执行对象
     */
    private String isBatchStartTarget;

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 系统代码
     * @return 系统代码
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置 系统代码
     * @param systemCode 系统代码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    /**
     * 取得 任务名称
     * @return 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置 任务名称
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * 取得 父任务代码
     * @return 父任务代码
     */
    public String getFtaskId() {
        return ftaskId;
    }

    /**
     * 设置 父任务代码
     * @param ftaskId 父任务代码
     */
    public void setFtaskId(String ftaskId) {
        this.ftaskId = ftaskId == null ? null : ftaskId.trim();
    }

    /**
     * 取得 批处理业务名称
     * @return 批处理业务名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置 批处理业务名称
     * @param beanName 批处理业务名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * 取得 任务优先级
     * @return 任务优先级
     */
    public Long getTaskPri() {
        return taskPri;
    }

    /**
     * 设置 任务优先级
     * @param taskPri 任务优先级
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    /**
     * 取得 是否批处理启动执行对象
     * @return 是否批处理启动执行对象
     */
    public String getIsBatchStartTarget() {
        return isBatchStartTarget;
    }

    /**
     * 设置 是否批处理启动执行对象
     * @param isBatchStartTarget 是否批处理启动执行对象
     */
    public void setIsBatchStartTarget(String isBatchStartTarget) {
        this.isBatchStartTarget = isBatchStartTarget == null ? null : isBatchStartTarget.trim();
    }
}