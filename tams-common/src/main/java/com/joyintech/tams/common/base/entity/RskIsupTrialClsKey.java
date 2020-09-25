package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupTrialClsKey extends BaseEntity {
    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 业务类型，模糊委托、预占信息、实占信息
     */
    private String tradeType;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号
     * @param tradeId 业务编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 业务类型，模糊委托、预占信息、实占信息
     * @return 业务类型，模糊委托、预占信息、实占信息
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 业务类型，模糊委托、预占信息、实占信息
     * @param tradeType 业务类型，模糊委托、预占信息、实占信息
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

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
}