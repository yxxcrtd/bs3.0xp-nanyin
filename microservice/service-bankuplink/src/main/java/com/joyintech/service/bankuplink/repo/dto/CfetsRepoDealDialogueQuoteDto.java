/*
 * 文件名：CfetsBondDealDialogueQuoteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.repo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 对话报价dto
 * 
 * @author zhangliang
 * @version 1.0 2020年5月13日
 * @see CfetsRepoDealDialogueQuoteDto
 * @since 1.0
 */
public class CfetsRepoDealDialogueQuoteDto extends CfetsRepoDealQuotedPrice001Dto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8293473776098112670L;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 交割日期
     */
    private Date settleDate;

    /**
     * 结算方式
     */
    private String settleMethod;

    /**
     * 清算类型
     */
    private String settleType;

    /**
     * 结算币种
     */
    private String settleCcy;

    /**
     * 应计利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 全价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 净价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 每百元金额(元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal parValue;

    /**
     * 应计利息总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 本金额(元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal parValueAmt;

    /**
     * 获取 quoteId
     * 
     * @return quoteId.
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 quoteId
     * 
     * @param quoteId quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * 获取 settleDate
     * 
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     * 
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 settleMethod
     * 
     * @return settleMethod.
     */
    public String getSettleMethod() {
        return settleMethod;
    }

    /**
     * 设置 settleMethod
     * 
     * @param settleMethod settleMethod
     */
    public void setSettleMethod(String settleMethod) {
        this.settleMethod = settleMethod;
    }

    /**
     * 获取 settleType
     * 
     * @return settleType.
     */
    public String getSettleType() {
        return settleType;
    }

    /**
     * 设置 settleType
     * 
     * @param settleType settleType
     */
    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    /**
     * 获取 settleCcy
     * 
     * @return settleCcy.
     */
    public String getSettleCcy() {
        return settleCcy;
    }

    /**
     * 设置 settleCcy
     * 
     * @param settleCcy settleCcy
     */
    public void setSettleCcy(String settleCcy) {
        this.settleCcy = settleCcy;
    }

    /**
     * 获取 fpriceAmt
     * 
     * @return fpriceAmt.
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 fpriceAmt
     * 
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 unitInt
     * 
     * @return unitInt.
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 unitInt
     * 
     * @param unitInt unitInt
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 获取 unitFprice
     * 
     * @return unitFprice.
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    /**
     * 设置 unitFprice
     * 
     * @param unitFprice unitFprice
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 获取 cpriceAmt
     * 
     * @return cpriceAmt.
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 cpriceAmt
     * 
     * @param cpriceAmt cpriceAmt
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 获取 parValue
     * 
     * @return parValue.
     */
    public BigDecimal getParValue() {
        return parValue;
    }

    /**
     * 设置 parValue
     * 
     * @param parValue parValue
     */
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    /**
     * 获取 intAmt
     * 
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     * 
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 parValueAmt
     * 
     * @return parValueAmt.
     */
    public BigDecimal getParValueAmt() {
        return parValueAmt;
    }

    /**
     * 设置 parValueAmt
     * 
     * @param parValueAmt parValueAmt
     */
    public void setParValueAmt(BigDecimal parValueAmt) {
        this.parValueAmt = parValueAmt;
    }

}
