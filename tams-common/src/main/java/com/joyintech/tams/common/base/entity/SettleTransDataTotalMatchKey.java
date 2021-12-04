package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SettleTransDataTotalMatchKey extends BaseEntity {
    /**
     * 成交数据明细的流水号
     */
    private String seqNo;

    /**
     * 指令编号
     */
    private String entrunstTradeId;

    /**
     * 匹配方式，手工匹配，自动匹配
     */
    private String matchClass;

    /**
     * 取得 成交数据明细的流水号
     * @return 成交数据明细的流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 成交数据明细的流水号
     * @param seqNo 成交数据明细的流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getEntrunstTradeId() {
        return entrunstTradeId;
    }

    /**
     * 设置 指令编号
     * @param entrunstTradeId 指令编号
     */
    public void setEntrunstTradeId(String entrunstTradeId) {
        this.entrunstTradeId = entrunstTradeId == null ? null : entrunstTradeId.trim();
    }

    /**
     * 取得 匹配方式，手工匹配，自动匹配
     * @return 匹配方式，手工匹配，自动匹配
     */
    public String getMatchClass() {
        return matchClass;
    }

    /**
     * 设置 匹配方式，手工匹配，自动匹配
     * @param matchClass 匹配方式，手工匹配，自动匹配
     */
    public void setMatchClass(String matchClass) {
        this.matchClass = matchClass == null ? null : matchClass.trim();
    }
}