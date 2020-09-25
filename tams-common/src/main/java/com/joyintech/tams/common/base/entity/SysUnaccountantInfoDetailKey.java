package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SysUnaccountantInfoDetailKey extends BaseEntity {
    /**
     * 本批次ID（对应主表SYS_UNACCOUNTANT_INFO-SEQ_NO）
     */
    private String seqNo;

    /**
     * 模板编码
     */
    private String modelCode;

    /**
     * 取得 本批次ID（对应主表SYS_UNACCOUNTANT_INFO-SEQ_NO）
     * @return 本批次ID（对应主表SYS_UNACCOUNTANT_INFO-SEQ_NO）
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 本批次ID（对应主表SYS_UNACCOUNTANT_INFO-SEQ_NO）
     * @param seqNo 本批次ID（对应主表SYS_UNACCOUNTANT_INFO-SEQ_NO）
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 模板编码
     * @return 模板编码
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * 设置 模板编码
     * @param modelCode 模板编码
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode == null ? null : modelCode.trim();
    }
}