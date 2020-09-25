package com.joyintech.tams.common.base.entity;

public class FinFloatCalcRule extends FinFloatCalcRuleKey {
    /**
     * 业绩基准规则
业绩基准规则 高水位？业绩基准？
业绩基准规则 高水位法、业绩基准法
     */
    private String baseRule;

    /**
     * 资产净值日类型
     */
    private String astVdateType;

    /**
     * 生效规则
     */
    private String actRule;

    /**
     * 备注
     */
    private String remark;

    /**
     * 基数类型，本金、资产净值、实收资本等
     */
    private String baseType;

    /**
     * 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    private String baseDateType;

    /**
     * 计息基础，A/360，A/365等
     */
    private String basis;

    /**
     * 取得 业绩基准规则
业绩基准规则 高水位？业绩基准？
业绩基准规则 高水位法、业绩基准法
     * @return 业绩基准规则
业绩基准规则 高水位？业绩基准？
业绩基准规则 高水位法、业绩基准法
     */
    public String getBaseRule() {
        return baseRule;
    }

    /**
     * 设置 业绩基准规则
业绩基准规则 高水位？业绩基准？
业绩基准规则 高水位法、业绩基准法
     * @param baseRule 业绩基准规则
业绩基准规则 高水位？业绩基准？
业绩基准规则 高水位法、业绩基准法
     */
    public void setBaseRule(String baseRule) {
        this.baseRule = baseRule == null ? null : baseRule.trim();
    }

    /**
     * 取得 资产净值日类型
     * @return 资产净值日类型
     */
    public String getAstVdateType() {
        return astVdateType;
    }

    /**
     * 设置 资产净值日类型
     * @param astVdateType 资产净值日类型
     */
    public void setAstVdateType(String astVdateType) {
        this.astVdateType = astVdateType == null ? null : astVdateType.trim();
    }

    /**
     * 取得 生效规则
     * @return 生效规则
     */
    public String getActRule() {
        return actRule;
    }

    /**
     * 设置 生效规则
     * @param actRule 生效规则
     */
    public void setActRule(String actRule) {
        this.actRule = actRule == null ? null : actRule.trim();
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
     * 取得 基数类型，本金、资产净值、实收资本等
     * @return 基数类型，本金、资产净值、实收资本等
     */
    public String getBaseType() {
        return baseType;
    }

    /**
     * 设置 基数类型，本金、资产净值、实收资本等
     * @param baseType 基数类型，本金、资产净值、实收资本等
     */
    public void setBaseType(String baseType) {
        this.baseType = baseType == null ? null : baseType.trim();
    }

    /**
     * 取得 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     * @return 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    public String getBaseDateType() {
        return baseDateType;
    }

    /**
     * 设置 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     * @param baseDateType 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    public void setBaseDateType(String baseDateType) {
        this.baseDateType = baseDateType == null ? null : baseDateType.trim();
    }

    /**
     * 取得 计息基础，A/360，A/365等
     * @return 计息基础，A/360，A/365等
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础，A/360，A/365等
     * @param basis 计息基础，A/360，A/365等
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }
}