package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InterfCmsInstitutionGrade extends BaseEntity {
    /**
     * 客户信息
     */
    private String gradeOrgId;

    /**
     * 评级类型
     */
    private String gradeType;

    /**
     * 评级认定结果
     */
    private String gradeResult;

    /**
     * 评级报表年度
     */
    private String gradeRptYear;

    /**
     * 使用模型类别
     */
    private String gradeModelType;

    /**
     * 评级生效日
     */
    private Date gradeEffDate;

    /**
     * 评级到期日
     */
    private Date gradeEndDate;

    /**
     * 取得 客户信息
     * @return 客户信息
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 设置 客户信息
     * @param gradeOrgId 客户信息
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 取得 评级类型
     * @return 评级类型
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 设置 评级类型
     * @param gradeType 评级类型
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    /**
     * 取得 评级认定结果
     * @return 评级认定结果
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 评级认定结果
     * @param gradeResult 评级认定结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 评级报表年度
     * @return 评级报表年度
     */
    public String getGradeRptYear() {
        return gradeRptYear;
    }

    /**
     * 设置 评级报表年度
     * @param gradeRptYear 评级报表年度
     */
    public void setGradeRptYear(String gradeRptYear) {
        this.gradeRptYear = gradeRptYear == null ? null : gradeRptYear.trim();
    }

    /**
     * 取得 使用模型类别
     * @return 使用模型类别
     */
    public String getGradeModelType() {
        return gradeModelType;
    }

    /**
     * 设置 使用模型类别
     * @param gradeModelType 使用模型类别
     */
    public void setGradeModelType(String gradeModelType) {
        this.gradeModelType = gradeModelType == null ? null : gradeModelType.trim();
    }

    /**
     * 取得 评级生效日
     * @return 评级生效日
     */
    public Date getGradeEffDate() {
        return gradeEffDate;
    }

    /**
     * 设置 评级生效日
     * @param gradeEffDate 评级生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setGradeEffDate(Date gradeEffDate) {
        this.gradeEffDate = gradeEffDate;
    }

    /**
     * 取得 评级到期日
     * @return 评级到期日
     */
    public Date getGradeEndDate() {
        return gradeEndDate;
    }

    /**
     * 设置 评级到期日
     * @param gradeEndDate 评级到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setGradeEndDate(Date gradeEndDate) {
        this.gradeEndDate = gradeEndDate;
    }
}