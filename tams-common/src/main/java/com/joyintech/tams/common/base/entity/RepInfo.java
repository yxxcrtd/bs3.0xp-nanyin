package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepInfo extends BaseEntity {
    /**
     * 报表ID
     */
    private String repId;

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板目录
     */
    private String templatePath;

    /**
     * null
     */
    private String templateName;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 报表状态
     */
    private String reportType;

    /**
     * null
     */
    private Long repOrder;

    /**
     * null
     */
    private String reportCategory;

    /**
     * 取得 报表ID
     * @return 报表ID
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 报表ID
     * @param repId 报表ID
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }

    /**
     * 取得 模板编号
     * @return 模板编号
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * 设置 模板编号
     * @param templateId 模板编号
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    /**
     * 取得 模板目录
     * @return 模板目录
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * 设置 模板目录
     * @param templatePath 模板目录
     */
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath == null ? null : templatePath.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置 null
     * @param templateName null
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    /**
     * 取得 报表名称
     * @return 报表名称
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * 设置 报表名称
     * @param reportName 报表名称
     */
    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    /**
     * 取得 报表状态
     * @return 报表状态
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 设置 报表状态
     * @param reportType 报表状态
     */
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getRepOrder() {
        return repOrder;
    }

    /**
     * 设置 null
     * @param repOrder null
     */
    public void setRepOrder(Long repOrder) {
        this.repOrder = repOrder;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getReportCategory() {
        return reportCategory;
    }

    /**
     * 设置 null
     * @param reportCategory null
     */
    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory == null ? null : reportCategory.trim();
    }
}