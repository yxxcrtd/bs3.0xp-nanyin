package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ModCurveMapping extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 曲线族代码
     */
    private String curveGroupCode;

    /**
     * 曲线组名称，企业债、国债收益率曲线
     */
    private String curveGroupName;

    /**
     * 曲线代码
     */
    private String curveCode;

    /**
     * 曲线类型
     */
    private String curveType;

    /**
     * 曲线名称
     */
    private String curveName;

    /**
     * 评级结果
     */
    private String gradeResult;

    /**
     * 评级结果名称
     */
    private String gradeResultName;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 曲线族代码
     * @return 曲线族代码
     */
    public String getCurveGroupCode() {
        return curveGroupCode;
    }

    /**
     * 设置 曲线族代码
     * @param curveGroupCode 曲线族代码
     */
    public void setCurveGroupCode(String curveGroupCode) {
        this.curveGroupCode = curveGroupCode == null ? null : curveGroupCode.trim();
    }

    /**
     * 取得 曲线组名称，企业债、国债收益率曲线
     * @return 曲线组名称，企业债、国债收益率曲线
     */
    public String getCurveGroupName() {
        return curveGroupName;
    }

    /**
     * 设置 曲线组名称，企业债、国债收益率曲线
     * @param curveGroupName 曲线组名称，企业债、国债收益率曲线
     */
    public void setCurveGroupName(String curveGroupName) {
        this.curveGroupName = curveGroupName == null ? null : curveGroupName.trim();
    }

    /**
     * 取得 曲线代码
     * @return 曲线代码
     */
    public String getCurveCode() {
        return curveCode;
    }

    /**
     * 设置 曲线代码
     * @param curveCode 曲线代码
     */
    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode == null ? null : curveCode.trim();
    }

    /**
     * 取得 曲线类型
     * @return 曲线类型
     */
    public String getCurveType() {
        return curveType;
    }

    /**
     * 设置 曲线类型
     * @param curveType 曲线类型
     */
    public void setCurveType(String curveType) {
        this.curveType = curveType == null ? null : curveType.trim();
    }

    /**
     * 取得 曲线名称
     * @return 曲线名称
     */
    public String getCurveName() {
        return curveName;
    }

    /**
     * 设置 曲线名称
     * @param curveName 曲线名称
     */
    public void setCurveName(String curveName) {
        this.curveName = curveName == null ? null : curveName.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 评级结果
     * @param gradeResult 评级结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 评级结果名称
     * @return 评级结果名称
     */
    public String getGradeResultName() {
        return gradeResultName;
    }

    /**
     * 设置 评级结果名称
     * @param gradeResultName 评级结果名称
     */
    public void setGradeResultName(String gradeResultName) {
        this.gradeResultName = gradeResultName == null ? null : gradeResultName.trim();
    }
}