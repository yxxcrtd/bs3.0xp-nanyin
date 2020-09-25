package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ProductRequirementAdd extends BaseEntity {
    /**
     * 产品需求编号
     */
    private String requirementId;

    /**
     * 初评结果
     */
    private String reportStatus;

    /**
     * 可行性状态，可行，不可行，待定
     */
    private String feasibilityStatus;

    /**
     * 可行需求分类，原有系列,新研发系列
     */
    private String feasibilityType;

    /**
     * null
     */
    private String feasibilityResult;

    /**
     * 上报说明
     */
    private String reportDecr;

    /**
     * 取得 产品需求编号
     * @return 产品需求编号
     */
    public String getRequirementId() {
        return requirementId;
    }

    /**
     * 设置 产品需求编号
     * @param requirementId 产品需求编号
     */
    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
    }

    /**
     * 取得 初评结果
     * @return 初评结果
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * 设置 初评结果
     * @param reportStatus 初评结果
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus == null ? null : reportStatus.trim();
    }

    /**
     * 取得 可行性状态，可行，不可行，待定
     * @return 可行性状态，可行，不可行，待定
     */
    public String getFeasibilityStatus() {
        return feasibilityStatus;
    }

    /**
     * 设置 可行性状态，可行，不可行，待定
     * @param feasibilityStatus 可行性状态，可行，不可行，待定
     */
    public void setFeasibilityStatus(String feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus == null ? null : feasibilityStatus.trim();
    }

    /**
     * 取得 可行需求分类，原有系列,新研发系列
     * @return 可行需求分类，原有系列,新研发系列
     */
    public String getFeasibilityType() {
        return feasibilityType;
    }

    /**
     * 设置 可行需求分类，原有系列,新研发系列
     * @param feasibilityType 可行需求分类，原有系列,新研发系列
     */
    public void setFeasibilityType(String feasibilityType) {
        this.feasibilityType = feasibilityType == null ? null : feasibilityType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getFeasibilityResult() {
        return feasibilityResult;
    }

    /**
     * 设置 null
     * @param feasibilityResult null
     */
    public void setFeasibilityResult(String feasibilityResult) {
        this.feasibilityResult = feasibilityResult == null ? null : feasibilityResult.trim();
    }

    /**
     * 取得 上报说明
     * @return 上报说明
     */
    public String getReportDecr() {
        return reportDecr;
    }

    /**
     * 设置 上报说明
     * @param reportDecr 上报说明
     */
    public void setReportDecr(String reportDecr) {
        this.reportDecr = reportDecr == null ? null : reportDecr.trim();
    }
}