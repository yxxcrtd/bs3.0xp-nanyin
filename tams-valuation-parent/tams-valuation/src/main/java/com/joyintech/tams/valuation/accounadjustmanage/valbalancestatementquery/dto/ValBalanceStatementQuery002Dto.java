package com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class ValBalanceStatementQuery002Dto extends BaseEntity {

    private static final long serialVersionUID = -7487584199512106517L;

    /**
     * 记账日期
     */
    private Date bookDate;

    /**
     * 记账流水号
     */
    private String seqNo;

    /**
     * 资产/负债名称
     */
    private String finprodAbbr;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 借贷
     */
    private String cdFlag;

    /**
     * 原币币种
     */
    private String ccyFmt;

    /**
     * 记账币种
     */
    private String bCcyFmt;

    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 业务类别
     */
    private String ywlb;

    /**
     * 原币金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal happenAmt;

    /**
     * 本币金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal bHappenAmt;

    /**
     * 数量
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal happenNumber;

    /**
     * 原币余额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal marketValue;

    /**
     * 本币余额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal valuePercent;

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCdFlag() {
        return cdFlag;
    }

    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    public String getCcyFmt() {
        return ccyFmt;
    }

    public void setCcyFmt(String ccyFmt) {
        this.ccyFmt = ccyFmt;
    }

    public String getbCcyFmt() {
        return bCcyFmt;
    }

    public void setbCcyFmt(String bCcyFmt) {
        this.bCcyFmt = bCcyFmt;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    public BigDecimal getbHappenAmt() {
        return bHappenAmt;
    }

    public void setbHappenAmt(BigDecimal bHappenAmt) {
        this.bHappenAmt = bHappenAmt;
    }

    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public BigDecimal getValuePercent() {
        return valuePercent;
    }

    public void setValuePercent(BigDecimal valuePercent) {
        this.valuePercent = valuePercent;
    }
}
