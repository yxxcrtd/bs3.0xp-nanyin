package com.joyintech.tams.invest.common.dto.cstp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.util.BigDecimalSerializer;

import java.math.BigDecimal;

public class EtrEntrustExt extends EtrEntrust {

    /**
     * 本方银行间机构
     */
    private String ouPartyId;

    /**
     * 对方银行间机构
     */
    private String cpPartyId;

    /**
     * 对方交易员
    */
    private String cpTrader;

    /**
     * 本方交易员
     */
    private String ouTrader;

    /**
     *功能描述:报价编号
     * @author zhangnian
     * @date 2021/1/18 0018
     * @param  * @param null
     * @return
     */
    private String quoteId;

    /**
     * 中债全价总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal cbFpriceAmt;

    /**
     * 上清全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scFpriceAmt;

    /**
     * 是否有报价委托标志
     */
    private String quoteIdFlag;
    /**
     * 是否多组合
     */
    private String isDzh;

    public String getOuPartyId() {
        return ouPartyId;
    }

    public void setOuPartyId(String ouPartyId) {
        this.ouPartyId = ouPartyId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public BigDecimal getCbFpriceAmt() {
        return cbFpriceAmt;
    }

    public void setCbFpriceAmt(BigDecimal cbFpriceAmt) {
        this.cbFpriceAmt = cbFpriceAmt;
    }

    public BigDecimal getScFpriceAmt() {
        return scFpriceAmt;
    }

    public void setScFpriceAmt(BigDecimal scFpriceAmt) {
        this.scFpriceAmt = scFpriceAmt;
    }

    public String getCpPartyId() {
        return cpPartyId;
    }

    public void setCpPartyId(String cpPartyId) {
        this.cpPartyId = cpPartyId;
    }

    public String getCpTrader() {
        return cpTrader;
    }

    public void setCpTrader(String cpTrader) {
        this.cpTrader = cpTrader;
    }

    public String getOuTrader() {
        return ouTrader;
    }

    public void setOuTrader(String ouTrader) {
        this.ouTrader = ouTrader;
    }

    public String getQuoteIdFlag() {
        return quoteIdFlag;
    }

    public void setQuoteIdFlag(String quoteIdFlag) {
        this.quoteIdFlag = quoteIdFlag;
    }

    public String getIsDzh() {
        return isDzh;
    }

    public void setIsDzh(String isDzh) {
        this.isDzh = isDzh;
    }
}
