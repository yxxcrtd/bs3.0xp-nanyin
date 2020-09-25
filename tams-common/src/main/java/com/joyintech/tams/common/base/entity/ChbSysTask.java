package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysTask extends BaseEntity {
    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 父任务代码
     */
    private String ftaskId;

    /**
     * 批处理业务名称
     */
    private String beanName;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务组代码 参照数据字典
     */
    private String taskClassCode;

    /**
     * 任务优先级
     */
    private Short taskPri;

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
     * 取得 任务组代码 参照数据字典
     * @return 任务组代码 参照数据字典
     */
    public String getTaskClassCode() {
        return taskClassCode;
    }

    /**
     * 设置 任务组代码 参照数据字典
     * @param taskClassCode 任务组代码 参照数据字典
     */
    public void setTaskClassCode(String taskClassCode) {
        this.taskClassCode = taskClassCode == null ? null : taskClassCode.trim();
    }

    /**
     * 取得 任务优先级
     * @return 任务优先级
     */
    public Short getTaskPri() {
        return taskPri;
    }

    /**
     * 设置 任务优先级
     * @param taskPri 任务优先级
     */
    public void setTaskPri(Short taskPri) {
        this.taskPri = taskPri;
    }
}