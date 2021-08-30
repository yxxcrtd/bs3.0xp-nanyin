package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ModBatchLogs extends BaseEntity {
    /**
     * 序号
     */
    private String seqno;

    /**
     * 批处理对应序号
     */
    private String batchExecSeqno;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 执行状态
     */
    private String execStatus;

    /**
     * 消息详情
     */
    private String execDetail;

    /**
     * 取得 序号
     * @return 序号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 序号
     * @param seqno 序号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 批处理对应序号
     * @return 批处理对应序号
     */
    public String getBatchExecSeqno() {
        return batchExecSeqno;
    }

    /**
     * 设置 批处理对应序号
     * @param batchExecSeqno 批处理对应序号
     */
    public void setBatchExecSeqno(String batchExecSeqno) {
        this.batchExecSeqno = batchExecSeqno == null ? null : batchExecSeqno.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

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
     * 取得 执行状态
     * @return 执行状态
     */
    public String getExecStatus() {
        return execStatus;
    }

    /**
     * 设置 执行状态
     * @param execStatus 执行状态
     */
    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus == null ? null : execStatus.trim();
    }

    /**
     * 取得 消息详情
     * @return 消息详情
     */
    public String getExecDetail() {
        return execDetail;
    }

    /**
     * 设置 消息详情
     * @param execDetail 消息详情
     */
    public void setExecDetail(String execDetail) {
        this.execDetail = execDetail == null ? null : execDetail.trim();
    }
}