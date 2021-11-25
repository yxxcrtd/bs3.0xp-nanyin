package com.joyintech.tams.svreport.chb.daycollect.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ChbDayCollect extends BaseEntity {
    //机构代码
    private String orgCode;
    //产品登记编码
    private String prdRegistCode;
    //产品代码
    private String prodId;
    //销售机构代码
    private String institutionCode;
    //地区类型
    private String areaCode;
    //投资者类型
    private String holderType;
    //币种
    private String ccy;
    //是否代销
    private String isagency;
    //汇总-总销售金额
    private BigDecimal daySaleAmountSum;
    //汇总-净销售金额
    private BigDecimal dayValueAmountSum;
    //确认净值
    private BigDecimal prodValue;
    //汇总-总销售份额
    private BigDecimal daySaleQtySum;
    //汇总-净销售份额
    private BigDecimal dayValueQtySum;
    //汇总-总销售金额（份额*净值）
    private BigDecimal saleTotalByQty;
    //汇总-净销售金额（份额*净值）
    private BigDecimal valueTotalByQty;
    //投资者数量
    private BigDecimal holderNum;
    //持有余额
    private BigDecimal holderBalanceAmt;
    //业务确认日期
    private Date equityConfDate;
    //备注
    private String remark;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getIsagency() {
        return isagency;
    }

    public void setIsagency(String isagency) {
        this.isagency = isagency;
    }

    public BigDecimal getDaySaleAmountSum() {
        return daySaleAmountSum;
    }

    public void setDaySaleAmountSum(BigDecimal daySaleAmountSum) {
        this.daySaleAmountSum = daySaleAmountSum;
    }

    public BigDecimal getDayValueAmountSum() {
        return dayValueAmountSum;
    }

    public void setDayValueAmountSum(BigDecimal dayValueAmountSum) {
        this.dayValueAmountSum = dayValueAmountSum;
    }

    public BigDecimal getProdValue() {
        return prodValue;
    }

    public void setProdValue(BigDecimal prodValue) {
        this.prodValue = prodValue;
    }

    public BigDecimal getDaySaleQtySum() {
        return daySaleQtySum;
    }

    public void setDaySaleQtySum(BigDecimal daySaleQtySum) {
        this.daySaleQtySum = daySaleQtySum;
    }

    public BigDecimal getDayValueQtySum() {
        return dayValueQtySum;
    }

    public void setDayValueQtySum(BigDecimal dayValueQtySum) {
        this.dayValueQtySum = dayValueQtySum;
    }

    public BigDecimal getSaleTotalByQty() {
        return saleTotalByQty;
    }

    public void setSaleTotalByQty(BigDecimal saleTotalByQty) {
        this.saleTotalByQty = saleTotalByQty;
    }

    public BigDecimal getValueTotalByQty() {
        return valueTotalByQty;
    }

    public void setValueTotalByQty(BigDecimal valueTotalByQty) {
        this.valueTotalByQty = valueTotalByQty;
    }

    public BigDecimal getHolderNum() {
        return holderNum;
    }

    public void setHolderNum(BigDecimal holderNum) {
        this.holderNum = holderNum;
    }

    public BigDecimal getHolderBalanceAmt() {
        return holderBalanceAmt;
    }

    public void setHolderBalanceAmt(BigDecimal holderBalanceAmt) {
        this.holderBalanceAmt = holderBalanceAmt;
    }

    public Date getEquityConfDate() {
        return equityConfDate;
    }

    public void setEquityConfDate(Date equityConfDate) {
        this.equityConfDate = equityConfDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}