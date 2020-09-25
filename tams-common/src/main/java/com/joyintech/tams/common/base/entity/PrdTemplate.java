package com.joyintech.tams.common.base.entity;

public class PrdTemplate extends PrdTemplateKey {
    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 处理模式
     */
    private String dealMode;

    /**
     * 发行方式
     */
    private String operationType;

    /**
     * 产品收益标识
     */
    private String profitFlag;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
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

    /**
     * 取得 处理模式
     * @return 处理模式
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 处理模式
     * @param dealMode 处理模式
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode == null ? null : dealMode.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置 发行方式
     * @param operationType 发行方式
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * 取得 产品收益标识
     * @return 产品收益标识
     */
    public String getProfitFlag() {
        return profitFlag;
    }

    /**
     * 设置 产品收益标识
     * @param profitFlag 产品收益标识
     */
    public void setProfitFlag(String profitFlag) {
        this.profitFlag = profitFlag == null ? null : profitFlag.trim();
    }

    /**
     * 取得 模板内容
     * @return 模板内容
     */
    public String getTemplateContent() {
        return templateContent;
    }

    /**
     * 设置 模板内容
     * @param templateContent 模板内容
     */
    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent == null ? null : templateContent.trim();
    }
}