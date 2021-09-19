package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PbcPrdBaseendSubNewHis extends BaseEntity {
    /**
     * null
     */
    private String registSeqno;

    /**
     * null
     */
    private String pbcProdId;

    /**
     * null
     */
    private Date mdate;

    /**
     * null
     */
    private String ccy;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profit;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitCny;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customProfit;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customProfitCny;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cusYearYield;

    /**
     * null
     */
    private String sendStatus;

    /**
     * null
     */
    private Date submitTime;

    /**
     * null
     */
    private String submitFeedbackStatus;

    /**
     * null
     */
    private String sumitSeq;

    /**
     * null
     */
    private Date statusDate;

    /**
     * null
     */
    private Date pbcSubmitDeadline;

    /**
     * null
     */
    private Date submitDeadline;

    /**
     * null
     */
    private String orgCode;

    /**
     * 取得 null
     * @return null
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 null
     * @param registSeqno null
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getPbcProdId() {
        return pbcProdId;
    }

    /**
     * 设置 null
     * @param pbcProdId null
     */
    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId == null ? null : pbcProdId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 null
     * @param mdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 null
     * @param ccy null
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 设置 null
     * @param profit null
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getProfitCny() {
        return profitCny;
    }

    /**
     * 设置 null
     * @param profitCny null
     */
    public void setProfitCny(BigDecimal profitCny) {
        this.profitCny = profitCny;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getCustomProfit() {
        return customProfit;
    }

    /**
     * 设置 null
     * @param customProfit null
     */
    public void setCustomProfit(BigDecimal customProfit) {
        this.customProfit = customProfit;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getCustomProfitCny() {
        return customProfitCny;
    }

    /**
     * 设置 null
     * @param customProfitCny null
     */
    public void setCustomProfitCny(BigDecimal customProfitCny) {
        this.customProfitCny = customProfitCny;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getCusYearYield() {
        return cusYearYield;
    }

    /**
     * 设置 null
     * @param cusYearYield null
     */
    public void setCusYearYield(BigDecimal cusYearYield) {
        this.cusYearYield = cusYearYield;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 null
     * @param sendStatus null
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 null
     * @param submitTime null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 null
     * @param submitFeedbackStatus null
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 null
     * @param sumitSeq null
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 null
     * @param statusDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    /**
     * 设置 null
     * @param pbcSubmitDeadline null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 null
     * @param submitDeadline null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 null
     * @param orgCode null
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}