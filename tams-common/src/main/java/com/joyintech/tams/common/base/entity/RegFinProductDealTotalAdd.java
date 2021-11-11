package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RegFinProductDealTotalAdd extends BaseEntity {
    /**
     * 汇总登记编号
     */
    private String totleTradeId;

    /**
     * 本方券交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ourShareAmt;

    /**
     * 融入券交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lendinShareAmt;

    /**
     * 卖空购回交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleinShareAmt;

    /**
     * 本方券净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ourCpriceAmt;

    /**
     * 融入券净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lendinCpriceAmt;

    /**
     * 本方券全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ourFpriceAmt;

    /**
     * 融入券全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lendinFpriceAmt;

    /**
     * 卖空购回全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleinFpriceAmt;

    /**
     * 本方券利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ourIntAmt;

    /**
     * 融入券利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lendinIntAmt;

    /**
     * 卖空购回利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleinIntAmt;

    /**
     * 实际行权日
     */
    private Date actSetteDate;

    /**
     * 取得 汇总登记编号
     * @return 汇总登记编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总登记编号
     * @param totleTradeId 汇总登记编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
    }

    /**
     * 取得 本方券交易份额
     * @return 本方券交易份额
     */
    public BigDecimal getOurShareAmt() {
        return ourShareAmt;
    }

    /**
     * 设置 本方券交易份额
     * @param ourShareAmt 本方券交易份额
     */
    public void setOurShareAmt(BigDecimal ourShareAmt) {
        this.ourShareAmt = ourShareAmt;
    }

    /**
     * 取得 融入券交易份额
     * @return 融入券交易份额
     */
    public BigDecimal getLendinShareAmt() {
        return lendinShareAmt;
    }

    /**
     * 设置 融入券交易份额
     * @param lendinShareAmt 融入券交易份额
     */
    public void setLendinShareAmt(BigDecimal lendinShareAmt) {
        this.lendinShareAmt = lendinShareAmt;
    }

    /**
     * 取得 卖空购回交易份额
     * @return 卖空购回交易份额
     */
    public BigDecimal getSaleinShareAmt() {
        return saleinShareAmt;
    }

    /**
     * 设置 卖空购回交易份额
     * @param saleinShareAmt 卖空购回交易份额
     */
    public void setSaleinShareAmt(BigDecimal saleinShareAmt) {
        this.saleinShareAmt = saleinShareAmt;
    }

    /**
     * 取得 本方券净价总额
     * @return 本方券净价总额
     */
    public BigDecimal getOurCpriceAmt() {
        return ourCpriceAmt;
    }

    /**
     * 设置 本方券净价总额
     * @param ourCpriceAmt 本方券净价总额
     */
    public void setOurCpriceAmt(BigDecimal ourCpriceAmt) {
        this.ourCpriceAmt = ourCpriceAmt;
    }

    /**
     * 取得 融入券净价总额
     * @return 融入券净价总额
     */
    public BigDecimal getLendinCpriceAmt() {
        return lendinCpriceAmt;
    }

    /**
     * 设置 融入券净价总额
     * @param lendinCpriceAmt 融入券净价总额
     */
    public void setLendinCpriceAmt(BigDecimal lendinCpriceAmt) {
        this.lendinCpriceAmt = lendinCpriceAmt;
    }

    /**
     * 取得 本方券全价总额
     * @return 本方券全价总额
     */
    public BigDecimal getOurFpriceAmt() {
        return ourFpriceAmt;
    }

    /**
     * 设置 本方券全价总额
     * @param ourFpriceAmt 本方券全价总额
     */
    public void setOurFpriceAmt(BigDecimal ourFpriceAmt) {
        this.ourFpriceAmt = ourFpriceAmt;
    }

    /**
     * 取得 融入券全价总额
     * @return 融入券全价总额
     */
    public BigDecimal getLendinFpriceAmt() {
        return lendinFpriceAmt;
    }

    /**
     * 设置 融入券全价总额
     * @param lendinFpriceAmt 融入券全价总额
     */
    public void setLendinFpriceAmt(BigDecimal lendinFpriceAmt) {
        this.lendinFpriceAmt = lendinFpriceAmt;
    }

    /**
     * 取得 卖空购回全价总额
     * @return 卖空购回全价总额
     */
    public BigDecimal getSaleinFpriceAmt() {
        return saleinFpriceAmt;
    }

    /**
     * 设置 卖空购回全价总额
     * @param saleinFpriceAmt 卖空购回全价总额
     */
    public void setSaleinFpriceAmt(BigDecimal saleinFpriceAmt) {
        this.saleinFpriceAmt = saleinFpriceAmt;
    }

    /**
     * 取得 本方券利息总额
     * @return 本方券利息总额
     */
    public BigDecimal getOurIntAmt() {
        return ourIntAmt;
    }

    /**
     * 设置 本方券利息总额
     * @param ourIntAmt 本方券利息总额
     */
    public void setOurIntAmt(BigDecimal ourIntAmt) {
        this.ourIntAmt = ourIntAmt;
    }

    /**
     * 取得 融入券利息总额
     * @return 融入券利息总额
     */
    public BigDecimal getLendinIntAmt() {
        return lendinIntAmt;
    }

    /**
     * 设置 融入券利息总额
     * @param lendinIntAmt 融入券利息总额
     */
    public void setLendinIntAmt(BigDecimal lendinIntAmt) {
        this.lendinIntAmt = lendinIntAmt;
    }

    /**
     * 取得 卖空购回利息总额
     * @return 卖空购回利息总额
     */
    public BigDecimal getSaleinIntAmt() {
        return saleinIntAmt;
    }

    /**
     * 设置 卖空购回利息总额
     * @param saleinIntAmt 卖空购回利息总额
     */
    public void setSaleinIntAmt(BigDecimal saleinIntAmt) {
        this.saleinIntAmt = saleinIntAmt;
    }

    /**
     * 取得 实际行权日
     * @return 实际行权日
     */
    public Date getActSetteDate() {
        return actSetteDate;
    }

    /**
     * 设置 实际行权日
     * @param actSetteDate 实际行权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActSetteDate(Date actSetteDate) {
        this.actSetteDate = actSetteDate;
    }
}