package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstInnerCreditRatingKey extends BaseEntity {
    /**
     * 评级对象，市场代码或者机构代码
     */
    private String oCode;

    /**
     * 评级对象类型，主体、债项
     */
    private String oType;

    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 评级日期
     */
    private Date gradeDate;

    /**
     * 取得 评级对象，市场代码或者机构代码
     * @return 评级对象，市场代码或者机构代码
     */
    public String getoCode() {
        return oCode;
    }

    /**
     * 设置 评级对象，市场代码或者机构代码
     * @param oCode 评级对象，市场代码或者机构代码
     */
    public void setoCode(String oCode) {
        this.oCode = oCode == null ? null : oCode.trim();
    }

    /**
     * 取得 评级对象类型，主体、债项
     * @return 评级对象类型，主体、债项
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级对象类型，主体、债项
     * @param oType 评级对象类型，主体、债项
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
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
}