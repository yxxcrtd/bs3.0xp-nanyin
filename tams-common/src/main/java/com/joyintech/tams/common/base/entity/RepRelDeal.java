package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepRelDeal extends BaseEntity {
    /**
     * 产品主代码
     */
    private String prodId;

    /**
     * 日期
     */
    private Date pubDate;

    /**
     * 期间投资关联机构发行证券交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bankTransAmt;

    /**
     * 期间投资关联机构承销证券交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orgTransAmt;

    /**
     * 期间关联方作为交易对手的投融资业务的交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investTransAmt;

    /**
     * 取得 产品主代码
     * @return 产品主代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品主代码
     * @param prodId 产品主代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getPubDate() {
        return pubDate;
    }

    /**
     * 设置 日期
     * @param pubDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 取得 期间投资关联机构发行证券交易金额
     * @return 期间投资关联机构发行证券交易金额
     */
    public BigDecimal getBankTransAmt() {
        return bankTransAmt;
    }

    /**
     * 设置 期间投资关联机构发行证券交易金额
     * @param bankTransAmt 期间投资关联机构发行证券交易金额
     */
    public void setBankTransAmt(BigDecimal bankTransAmt) {
        this.bankTransAmt = bankTransAmt;
    }

    /**
     * 取得 期间投资关联机构承销证券交易金额
     * @return 期间投资关联机构承销证券交易金额
     */
    public BigDecimal getOrgTransAmt() {
        return orgTransAmt;
    }

    /**
     * 设置 期间投资关联机构承销证券交易金额
     * @param orgTransAmt 期间投资关联机构承销证券交易金额
     */
    public void setOrgTransAmt(BigDecimal orgTransAmt) {
        this.orgTransAmt = orgTransAmt;
    }

    /**
     * 取得 期间关联方作为交易对手的投融资业务的交易金额
     * @return 期间关联方作为交易对手的投融资业务的交易金额
     */
    public BigDecimal getInvestTransAmt() {
        return investTransAmt;
    }

    /**
     * 设置 期间关联方作为交易对手的投融资业务的交易金额
     * @param investTransAmt 期间关联方作为交易对手的投融资业务的交易金额
     */
    public void setInvestTransAmt(BigDecimal investTransAmt) {
        this.investTransAmt = investTransAmt;
    }
}