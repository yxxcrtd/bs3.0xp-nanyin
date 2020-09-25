package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbWflBusinessNodeKey extends BaseEntity {
    /**
     * 流程代码
     */
    private String seqno;

    /**
     * 履历番号（流水号单位采番）
     */
    private Integer hisNo;

    /**
     * 取得 流程代码
     * @return 流程代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流程代码
     * @param seqno 流程代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 履历番号（流水号单位采番）
     * @return 履历番号（流水号单位采番）
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 履历番号（流水号单位采番）
     * @param hisNo 履历番号（流水号单位采番）
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }
}