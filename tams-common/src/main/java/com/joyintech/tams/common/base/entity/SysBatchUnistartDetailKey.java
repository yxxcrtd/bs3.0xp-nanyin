package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class SysBatchUnistartDetailKey extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 明细流水号，对应任务组执行信息流水号
     */
    private String detailSeqNo;

    /**
     * 顺序
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orderNo;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 明细流水号，对应任务组执行信息流水号
     * @return 明细流水号，对应任务组执行信息流水号
     */
    public String getDetailSeqNo() {
        return detailSeqNo;
    }

    /**
     * 设置 明细流水号，对应任务组执行信息流水号
     * @param detailSeqNo 明细流水号，对应任务组执行信息流水号
     */
    public void setDetailSeqNo(String detailSeqNo) {
        this.detailSeqNo = detailSeqNo == null ? null : detailSeqNo.trim();
    }

    /**
     * 取得 顺序
     * @return 顺序
     */
    public BigDecimal getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 顺序
     * @param orderNo 顺序
     */
    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }
}