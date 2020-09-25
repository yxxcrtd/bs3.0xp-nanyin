package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class CashArtDeal extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 资金账号
     */
    private String acctId;

    /**
     * 调整日期
     */
    private Date adjDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 调整金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal adjAmt;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 资金账号
     * @return 资金账号
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * 设置 资金账号
     * @param acctId 资金账号
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    /**
     * 取得 调整日期
     * @return 调整日期
     */
    public Date getAdjDate() {
        return adjDate;
    }

    /**
     * 设置 调整日期
     * @param adjDate 调整日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAdjDate(Date adjDate) {
        this.adjDate = adjDate;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 调整金额
     * @return 调整金额
     */
    public BigDecimal getAdjAmt() {
        return adjAmt;
    }

    /**
     * 设置 调整金额
     * @param adjAmt 调整金额
     */
    public void setAdjAmt(BigDecimal adjAmt) {
        this.adjAmt = adjAmt;
    }
}