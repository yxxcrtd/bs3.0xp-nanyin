package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokVouchRelKey extends BaseEntity {
    /**
     * 凭证子表编号
     */
    private String vouchSubnum;

    /**
     * 账务明细流水号
     */
    private String seqno;

    /**
     * 凭证编号
     */
    private String vouchNum;

    /**
     * 取得 凭证子表编号
     * @return 凭证子表编号
     */
    public String getVouchSubnum() {
        return vouchSubnum;
    }

    /**
     * 设置 凭证子表编号
     * @param vouchSubnum 凭证子表编号
     */
    public void setVouchSubnum(String vouchSubnum) {
        this.vouchSubnum = vouchSubnum == null ? null : vouchSubnum.trim();
    }

    /**
     * 取得 账务明细流水号
     * @return 账务明细流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 账务明细流水号
     * @param seqno 账务明细流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getVouchNum() {
        return vouchNum;
    }

    /**
     * 设置 凭证编号
     * @param vouchNum 凭证编号
     */
    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum == null ? null : vouchNum.trim();
    }
}