package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflBusinessNodeKey extends BaseEntity {
    /**
     * 流程代码
     */
    private String seqNo;

    /**
     * 履历番号（流水号单位采番）
     */
    private Long hisNo;

    /**
     * 取得 流程代码
     * @return 流程代码
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流程代码
     * @param seqNo 流程代码
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 履历番号（流水号单位采番）
     * @return 履历番号（流水号单位采番）
     */
    public Long getHisNo() {
        return hisNo;
    }

    /**
     * 设置 履历番号（流水号单位采番）
     * @param hisNo 履历番号（流水号单位采番）
     */
    public void setHisNo(Long hisNo) {
        this.hisNo = hisNo;
    }
}