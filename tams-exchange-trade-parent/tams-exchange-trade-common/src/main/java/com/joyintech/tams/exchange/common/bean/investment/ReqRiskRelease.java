package com.joyintech.tams.exchange.common.bean.investment;

import java.math.BigDecimal;

/**
 * 风控释放请求bean
 */
public class ReqRiskRelease {


    /**
     * 实占编号
     */
    private String eventId;

    /**
     * 交易日期
     */
    private String eventDate;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 价格
     */
    private BigDecimal priceAmt;

    /**
     * 数量
     */
    private Long tradeNum;

    /**
     * 撤销实占标志
     */
    private String revokeFlg;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 释放流水号(唯一性标识、部分释放时用于标识是否做过该笔释放)
     */
    private String seqNo;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPriceAmt() {
        return priceAmt;
    }

    public void setPriceAmt(BigDecimal priceAmt) {
        this.priceAmt = priceAmt;
    }

    public Long getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Long tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getRevokeFlg() {
        return revokeFlg;
    }

    public void setRevokeFlg(String revokeFlg) {
        this.revokeFlg = revokeFlg;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
}
