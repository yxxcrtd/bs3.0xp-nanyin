package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValPrdPayRule extends BaseEntity {
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
     * 付息规则
     */
    private String payRule;

    /**
     * 付息周期
     */
    private String payCycle;

    /**
     * 付息周期单位
     */
    private String payCycleUnit;

    /**
     * 推算方法
     */
    private String calculation;

    /**
     * 首次付息日
     */
    private Date fstPayDate;

    /**
     * 营业日准则
     */
    private String tdayRule;

    /**
     * 生效日期
     */
    private Date vdate;

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
     * 取得 付息规则
     * @return 付息规则
     */
    public String getPayRule() {
        return payRule;
    }

    /**
     * 设置 付息规则
     * @param payRule 付息规则
     */
    public void setPayRule(String payRule) {
        this.payRule = payRule == null ? null : payRule.trim();
    }

    /**
     * 取得 付息周期
     * @return 付息周期
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 付息周期
     * @param payCycle 付息周期
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle == null ? null : payCycle.trim();
    }

    /**
     * 取得 付息周期单位
     * @return 付息周期单位
     */
    public String getPayCycleUnit() {
        return payCycleUnit;
    }

    /**
     * 设置 付息周期单位
     * @param payCycleUnit 付息周期单位
     */
    public void setPayCycleUnit(String payCycleUnit) {
        this.payCycleUnit = payCycleUnit == null ? null : payCycleUnit.trim();
    }

    /**
     * 取得 推算方法
     * @return 推算方法
     */
    public String getCalculation() {
        return calculation;
    }

    /**
     * 设置 推算方法
     * @param calculation 推算方法
     */
    public void setCalculation(String calculation) {
        this.calculation = calculation == null ? null : calculation.trim();
    }

    /**
     * 取得 首次付息日
     * @return 首次付息日
     */
    public Date getFstPayDate() {
        return fstPayDate;
    }

    /**
     * 设置 首次付息日
     * @param fstPayDate 首次付息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFstPayDate(Date fstPayDate) {
        this.fstPayDate = fstPayDate;
    }

    /**
     * 取得 营业日准则
     * @return 营业日准则
     */
    public String getTdayRule() {
        return tdayRule;
    }

    /**
     * 设置 营业日准则
     * @param tdayRule 营业日准则
     */
    public void setTdayRule(String tdayRule) {
        this.tdayRule = tdayRule == null ? null : tdayRule.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 生效日期
     * @param vdate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
}