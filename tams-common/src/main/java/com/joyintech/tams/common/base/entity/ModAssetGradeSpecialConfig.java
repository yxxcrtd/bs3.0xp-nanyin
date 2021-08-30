package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ModAssetGradeSpecialConfig extends BaseEntity {
    /**
     * null
     */
    private String seqno;

    /**
     * null
     */
    private String finprodId;

    /**
     * null
     */
    private String finprodName;

    /**
     * null
     */
    private String gradeOrgId;

    /**
     * null
     */
    private String oType;

    /**
     * 评级机构类型
     */
    private String gradeOrgType;

    /**
     * 取得 null
     * @return null
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 null
     * @param seqno null
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 null
     * @param finprodId null
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 null
     * @param finprodName null
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 设置 null
     * @param gradeOrgId null
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 null
     * @param oType null
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }

    /**
     * 取得 评级机构类型
     * @return 评级机构类型
     */
    public String getGradeOrgType() {
        return gradeOrgType;
    }

    /**
     * 设置 评级机构类型
     * @param gradeOrgType 评级机构类型
     */
    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType == null ? null : gradeOrgType.trim();
    }
}