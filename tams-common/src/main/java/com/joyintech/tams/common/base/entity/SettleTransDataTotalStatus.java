package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SettleTransDataTotalStatus extends BaseEntity {
    /**
     * 汇总成交数据的流水
     */
    private String seqNo;

    /**
     * 匹配状态
     */
    private String matchStatus;

    /**
     * 取得 汇总成交数据的流水
     * @return 汇总成交数据的流水
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 汇总成交数据的流水
     * @param seqNo 汇总成交数据的流水
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 匹配状态
     * @return 匹配状态
     */
    public String getMatchStatus() {
        return matchStatus;
    }

    /**
     * 设置 匹配状态
     * @param matchStatus 匹配状态
     */
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus == null ? null : matchStatus.trim();
    }
}