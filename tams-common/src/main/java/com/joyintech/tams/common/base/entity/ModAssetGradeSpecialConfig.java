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

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getGradeOrgType() {
        return gradeOrgType;
    }

    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }
}