package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValFloatCalcRuleC extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 生效规则
     */
    private String actRule;

    /**
     * 资产净值日
     */
    private String astVluDate;

    /**
     * 业绩报酬计算规则
     */
    private String calRule;

    /**
     * 计费基础
     */
    private String chargeBase;

    /**
     * 计费基数
     */
    private String chargeBasis;

    /**
     * 计费基数日期类型
     */
    private String basisDate;

    /**
     * 取得 计费基础
     * @return 计费基础
     */
    public String getChargeBase() {
        return chargeBase;
    }

    /**
     * 设置 计费基础
     * @param chargeBase 计费基础
     */
    public void setChargeBase(String chargeBase) {
        this.chargeBase = chargeBase == null ? null : chargeBase.trim();
    }

    /**
     * 取得 计费基数
     * @return 计费基数
     */
    public String getChargeBasis() {
        return chargeBasis;
    }

    /**
     * 设置 计费基数
     * @param chargeBasis 计费基数
     */
    public void setChargeBasis(String chargeBasis) {
        this.chargeBasis = chargeBasis == null ? null : chargeBasis.trim();
    }

    /**
     * 取得 计费基数日期类型
     * @return 计费基数日期类型
     */
    public String getBasisDate() {
        return basisDate;
    }

    /**
     * 设置 计费基数日期类型
     * @param basisDate 计费基数日期类型
     */
    public void setBasisDate(String basisDate) {
        this.basisDate = basisDate == null ? null : basisDate.trim();
    }


    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 产品起息日
     * @param prodVdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 费用ID
     * @return 费用ID
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用ID
     * @param feeId 费用ID
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
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
     * 取得 资产净值日
     * @return 资产净值日
     */
    public String getAstVluDate() {
        return astVluDate;
    }

    /**
     * 设置 资产净值日
     * @param astVluDate 资产净值日
     */
    public void setAstVluDate(String astVluDate) {
        this.astVluDate = astVluDate == null ? null : astVluDate.trim();
    }

    /**
     * 取得 业绩报酬计算规则
     * @return 业绩报酬计算规则
     */
    public String getCalRule() {
        return calRule;
    }

    /**
     * 设置 业绩报酬计算规则
     * @param calRule 业绩报酬计算规则
     */
    public void setCalRule(String calRule) {
        this.calRule = calRule == null ? null : calRule.trim();
    }
}