package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdFeeDealC extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 资管系统的交易编号
     */
    private String tradeId;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 计提开始日
     */
    private Date calStartDay;

    /**
     * 计提结束日
     */
    private Date calEndDay;

    /**
     * 实付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actPayAmt;

    /**
     * 是否结算，收息付费时用
     */
    private String isClean2;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 默认：人民币
     */
    private String ccy;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

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
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 新增、撤销
     * @return 新增、撤销
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、撤销
     * @param dataActionSign 新增、撤销
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 资管系统的交易编号
     * @return 资管系统的交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 资管系统的交易编号
     * @param tradeId 资管系统的交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
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
     * 取得 计提开始日
     * @return 计提开始日
     */
    public Date getCalStartDay() {
        return calStartDay;
    }

    /**
     * 设置 计提开始日
     * @param calStartDay 计提开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCalStartDay(Date calStartDay) {
        this.calStartDay = calStartDay;
    }

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public Date getCalEndDay() {
        return calEndDay;
    }

    /**
     * 设置 计提结束日
     * @param calEndDay 计提结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCalEndDay(Date calEndDay) {
        this.calEndDay = calEndDay;
    }

    /**
     * 取得 实付费用
     * @return 实付费用
     */
    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }

    /**
     * 设置 实付费用
     * @param actPayAmt 实付费用
     */
    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

    /**
     * 取得 是否结算，收息付费时用
     * @return 是否结算，收息付费时用
     */
    public String getIsClean2() {
        return isClean2;
    }

    /**
     * 设置 是否结算，收息付费时用
     * @param isClean2 是否结算，收息付费时用
     */
    public void setIsClean2(String isClean2) {
        this.isClean2 = isClean2 == null ? null : isClean2.trim();
    }

    /**
     * 取得 支付日
     * @return 支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 支付日
     * @param payDate 支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 默认：人民币
     * @return 默认：人民币
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 默认：人民币
     * @param ccy 默认：人民币
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 有无清算指令
     * @return 有无清算指令
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 有无清算指令
     * @param isClean 有无清算指令
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean == null ? null : isClean.trim();
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
}