package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdDeclareSchemeDoc extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 附件类型
     */
    private String docType;

    /**
     * 模板类型
     */
    private String tplType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 附件类型
     * @return 附件类型
     */
    public String getDocType() {
        return docType;
    }

    /**
     * 设置 附件类型
     * @param docType 附件类型
     */
    public void setDocType(String docType) {
        this.docType = docType == null ? null : docType.trim();
    }

    /**
     * 取得 模板类型
     * @return 模板类型
     */
    public String getTplType() {
        return tplType;
    }

    /**
     * 设置 模板类型
     * @param tplType 模板类型
     */
    public void setTplType(String tplType) {
        this.tplType = tplType == null ? null : tplType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}