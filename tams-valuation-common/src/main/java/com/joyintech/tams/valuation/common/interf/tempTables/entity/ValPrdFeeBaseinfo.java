package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdFeeBaseinfo extends BaseEntity {
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
     * 费用名称
     */
    private String feeName;

    /**
     * 初始费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal initRate;

    /**
     * 计费基础
     */
    private String chargeBase;

    /**
     * 计费基数
     */
    private String chargeBasic;

    /**
     * 付费规则
     */
    private String payRule;

    /**
     * 营业准则
     */
    private String tdayRule;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 计费基数日期类型
     */
    private String basisDate;

    /**
     * 生效日期
     */
    private Date vdate;

    /**
     * 产品简称
     */
    private String prodAbbr;

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
     * 取得 费用名称
     * @return 费用名称
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 费用名称
     * @param feeName 费用名称
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName == null ? null : feeName.trim();
    }

    /**
     * 取得 初始费率
     * @return 初始费率
     */
    public BigDecimal getInitRate() {
        return initRate;
    }

    /**
     * 设置 初始费率
     * @param initRate 初始费率
     */
    public void setInitRate(BigDecimal initRate) {
        this.initRate = initRate;
    }

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
    public String getChargeBasic() {
        return chargeBasic;
    }

    /**
     * 设置 计费基数
     * @param chargeBasic 计费基数
     */
    public void setChargeBasic(String chargeBasic) {
        this.chargeBasic = chargeBasic == null ? null : chargeBasic.trim();
    }

    /**
     * 取得 付费规则
     * @return 付费规则
     */
    public String getPayRule() {
        return payRule;
    }

    /**
     * 设置 付费规则
     * @param payRule 付费规则
     */
    public void setPayRule(String payRule) {
        this.payRule = payRule == null ? null : payRule.trim();
    }

    /**
     * 取得 营业准则
     * @return 营业准则
     */
    public String getTdayRule() {
        return tdayRule;
    }

    /**
     * 设置 营业准则
     * @param tdayRule 营业准则
     */
    public void setTdayRule(String tdayRule) {
        this.tdayRule = tdayRule == null ? null : tdayRule.trim();
    }

    /**
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
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

    /**
     * 取得 产品简称
     * @return 产品简称
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 产品简称
     * @param prodAbbr 产品简称
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr == null ? null : prodAbbr.trim();
    }
}