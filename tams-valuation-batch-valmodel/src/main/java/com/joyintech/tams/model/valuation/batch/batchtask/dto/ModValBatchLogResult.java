package com.joyintech.tams.model.valuation.batch.batchtask.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.util.Date;

public class ModValBatchLogResult extends BaseEntity {
    /**
     * 序号
     */
    private String seqno;

    /**
     * 日志信息
     */
    private String msg;
    /**
     * 异常类型
     * 枚举 ModelDictCode.批处理执行结果
     */
    private String type;

    /**
     * 批处理对应序号
     */
    private String batchExecSeqno;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 执行日期
     */
    private Date execDate;

    /**
     * 任务号
     */
    private String taskId;

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
     * 取得 执行日期
     * @return 执行日期
     */
    public Date getExecDate() {
        return execDate;
    }

    /**
     * 设置 执行日期
     * @param execDate 执行日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}