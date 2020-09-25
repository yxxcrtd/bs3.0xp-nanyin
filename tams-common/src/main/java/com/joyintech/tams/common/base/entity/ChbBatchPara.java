package com.joyintech.tams.common.base.entity;

/**
 * 描述:CHB_BATCH_PARA表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-11-22
 */
public class ChbBatchPara {
    /**
     * 主键id
     */
    private String paraId;

    /**
     * 批任务id
     */
    private String taskId;

    /**
     * 参数
     */
    private String paras;

    /**
     * 主键id
     * @return PARA_ID 主键id
     */
    public String getParaId() {
        return paraId;
    }

    /**
     * 主键id
     * @param paraId 主键id
     */
    public void setParaId(String paraId) {
        this.paraId = paraId == null ? null : paraId.trim();
    }

    /**
     * 批任务id
     * @return TASK_ID 批任务id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 批任务id
     * @param taskId 批任务id
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 参数
     * @return PARAS 参数
     */
    public String getParas() {
        return paras;
    }

    /**
     * 参数
     * @param paras 参数
     */
    public void setParas(String paras) {
        this.paras = paras == null ? null : paras.trim();
    }
}