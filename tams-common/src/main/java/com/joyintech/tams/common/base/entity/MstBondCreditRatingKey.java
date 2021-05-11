package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;

public class MstBondCreditRatingKey extends BaseEntity {
    /**
     * 债券代码（市场代码）
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String oCode;

    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 评级日期
     */
    private Date gradeDate;

    /**
     * 评级类型，主体评级、债项评级
     */
    private String oType;

    /**
     * 取得 债券代码（市场代码）
     * @return 债券代码（市场代码）
     */
    public String getoCode() {
        return oCode;
    }

    /**
     * 设置 债券代码（市场代码）
     * @param oCode 债券代码（市场代码）
     */
    public void setoCode(String oCode) {
        this.oCode = oCode == null ? null : oCode.trim();
    }

    /**
     * 取得 评级机构
     * @return 评级机构
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 设置 评级机构
     * @param gradeOrgId 评级机构
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 取得 评级日期
     * @return 评级日期
     */
    public Date getGradeDate() {
        return gradeDate;
    }

    /**
     * 设置 评级日期
     * @param gradeDate 评级日期
     */
    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    /**
     * 取得 评级类型，主体评级、债项评级
     * @return 评级类型，主体评级、债项评级
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级类型，主体评级、债项评级
     * @param oType 评级类型，主体评级、债项评级
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }
}