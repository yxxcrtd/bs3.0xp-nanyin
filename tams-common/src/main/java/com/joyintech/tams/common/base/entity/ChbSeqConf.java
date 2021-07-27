package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSeqConf extends BaseEntity {
    /**
     * 序列代码
     */
    private String seqCode;

    /**
     * 当前序号
     */
    private Long seqValue;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 序列代码
     * @return 序列代码
     */
    public String getSeqCode() {
        return seqCode;
    }

    /**
     * 设置 序列代码
     * @param seqCode 序列代码
     */
    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode == null ? null : seqCode.trim();
    }

    /**
     * 取得 当前序号
     * @return 当前序号
     */
    public Long getSeqValue() {
        return seqValue;
    }

    /**
     * 设置 当前序号
     * @param seqValue 当前序号
     */
    public void setSeqValue(Long seqValue) {
        this.seqValue = seqValue;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}