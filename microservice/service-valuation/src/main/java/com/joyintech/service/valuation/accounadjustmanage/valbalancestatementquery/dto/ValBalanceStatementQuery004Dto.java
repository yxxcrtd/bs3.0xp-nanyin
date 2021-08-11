package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class ValBalanceStatementQuery004Dto extends BaseEntity {

    private static final long serialVersionUID = -2652960037721481034L;

    /**
     * 	帳套代碼
     */
    private String booksetId;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 资产代碼
     */
    private String finprodId;

    /**
     * 资产类型
     */
    private String finprodType;

    /**
     * 业务類型
     */
    private String busiType;

    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 交易平台
     */
    private String tradePlay;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 券面总额/交易金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 交易方向
     */
    private String psCode;

    public String getPsCode() {
        return psCode;
    }

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradePlay() {
        return tradePlay;
    }

    public void setTradePlay(String tradePlay) {
        this.tradePlay = tradePlay;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }
}
