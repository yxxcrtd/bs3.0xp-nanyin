package com.joyintech.service.valuation.lossmodel.batchtask.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.util.Date;

public class ModValBatchParams extends BaseEntity {
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
     * 执行日期
     */
    private Date execDate;

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
}