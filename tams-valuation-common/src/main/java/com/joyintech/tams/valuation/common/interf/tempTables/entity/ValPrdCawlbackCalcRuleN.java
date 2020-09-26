package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValPrdCawlbackCalcRuleN extends BaseEntity {
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
     * 生效日
     */
    private Date effDate;

    /**
     * 回补规则
     */
    private String cawlbackRule;

    /**
     * 回补方式
     */
    private String cawlbackType;

    /**
     * 计算基础
     */
    private String chargeBase;

    /**
     * 生效规则
     */
    private String actRule;

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
     * 取得 回补规则
     * @return 回补规则
     */
    public String getCawlbackRule() {
        return cawlbackRule;
    }

    /**
     * 设置 回补规则
     * @param cawlbackRule 回补规则
     */
    public void setCawlbackRule(String cawlbackRule) {
        this.cawlbackRule = cawlbackRule == null ? null : cawlbackRule.trim();
    }

    /**
     * 取得 回补方式
     * @return 回补方式
     */
    public String getCawlbackType() {
        return cawlbackType;
    }

    /**
     * 设置 回补方式
     * @param cawlbackType 回补方式
     */
    public void setCawlbackType(String cawlbackType) {
        this.cawlbackType = cawlbackType == null ? null : cawlbackType.trim();
    }

    /**
     * 取得 计算基础
     * @return 计算基础
     */
    public String getChargeBase() {
        return chargeBase;
    }

    /**
     * 设置 计算基础
     * @param chargeBase 计算基础
     */
    public void setChargeBase(String chargeBase) {
        this.chargeBase = chargeBase == null ? null : chargeBase.trim();
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
}