package com.joyintech.tams.common.base.entity;

public class FinProductValuationPara extends FinProductValuationParaKey {
    /**
     * 净值数据延迟天数
     */
    private String intAccrualMode;

    /**
     * 税务方案代码
     */
    private String taxScheId;

    /**
     * 估值表模板
     */
    private String valuationTemplate;

    /**
     * 交易所回购计提天数方式
     */
    private String seRepDayType;

    /**
     * 取得 净值数据延迟天数
     * @return 净值数据延迟天数
     */
    public String getIntAccrualMode() {
        return intAccrualMode;
    }

    /**
     * 设置 净值数据延迟天数
     * @param intAccrualMode 净值数据延迟天数
     */
    public void setIntAccrualMode(String intAccrualMode) {
        this.intAccrualMode = intAccrualMode == null ? null : intAccrualMode.trim();
    }

    /**
     * 取得 税务方案代码
     * @return 税务方案代码
     */
    public String getTaxScheId() {
        return taxScheId;
    }

    /**
     * 设置 税务方案代码
     * @param taxScheId 税务方案代码
     */
    public void setTaxScheId(String taxScheId) {
        this.taxScheId = taxScheId == null ? null : taxScheId.trim();
    }

    /**
     * 取得 估值表模板
     * @return 估值表模板
     */
    public String getValuationTemplate() {
        return valuationTemplate;
    }

    /**
     * 设置 估值表模板
     * @param valuationTemplate 估值表模板
     */
    public void setValuationTemplate(String valuationTemplate) {
        this.valuationTemplate = valuationTemplate == null ? null : valuationTemplate.trim();
    }

    /**
     * 取得 交易所回购计提天数方式
     * @return 交易所回购计提天数方式
     */
    public String getSeRepDayType() {
        return seRepDayType;
    }

    /**
     * 设置 交易所回购计提天数方式
     * @param seRepDayType 交易所回购计提天数方式
     */
    public void setSeRepDayType(String seRepDayType) {
        this.seRepDayType = seRepDayType == null ? null : seRepDayType.trim();
    }
}