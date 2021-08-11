package com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class ValBalanceStatementQuery003Dto extends BaseEntity {

    private static final long serialVersionUID = 8832732077582724120L;

    /**
     * 记账日期
     */
    private Date bookDate;

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
     * 冲销标识
     */
    private String offsetFlag;

    /**
     * 凭证类型
     */
    private String bookType;

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
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

    public String getOffsetFlag() {
        return offsetFlag;
    }

    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
