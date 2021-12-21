package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlSecReconConfig extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 业务标识
     */
    private String ywbs;

    /**
     * 交易类别
     */
    private String jylb;

    /**
     * 摘要标识
     */
    private String zybs;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序列号
     * @param seqNo 序列号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 业务标识
     * @return 业务标识
     */
    public String getYwbs() {
        return ywbs;
    }

    /**
     * 设置 业务标识
     * @param ywbs 业务标识
     */
    public void setYwbs(String ywbs) {
        this.ywbs = ywbs == null ? null : ywbs.trim();
    }

    /**
     * 取得 交易类别
     * @return 交易类别
     */
    public String getJylb() {
        return jylb;
    }

    /**
     * 设置 交易类别
     * @param jylb 交易类别
     */
    public void setJylb(String jylb) {
        this.jylb = jylb == null ? null : jylb.trim();
    }

    /**
     * 取得 摘要标识
     * @return 摘要标识
     */
    public String getZybs() {
        return zybs;
    }

    /**
     * 设置 摘要标识
     * @param zybs 摘要标识
     */
    public void setZybs(String zybs) {
        this.zybs = zybs == null ? null : zybs.trim();
    }
}