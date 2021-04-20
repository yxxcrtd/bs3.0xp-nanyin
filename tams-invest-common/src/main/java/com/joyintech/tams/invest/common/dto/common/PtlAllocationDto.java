package com.joyintech.tams.invest.common.dto.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/2/26
 */
public class PtlAllocationDto extends EtrEntrust {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8967384621506273L;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;
    /**
     *  组合名称
     */
    private String portfolioName;
    /**
     * 期限天数，到期日-起息日
     */
    private Long termDays;

    /**
     * 剩余额度
     */
    private BigDecimal avaNum;

    /**
     * 全价总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 交易本金(中债登)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbFpriceAmt;

    /**
     * 交易本金(上清所)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scFpriceAmt;

    /**
     * 交易费用LIST
     */
    private List<TrdTradeFeeDetail> tradeFeeList;

    public Long getTermDays() {
        return termDays;
    }

    public void setTermDays(Long termDays) {
        this.termDays = termDays;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public BigDecimal getAvaNum() {
        return avaNum;
    }

    public void setAvaNum(BigDecimal avaNum) {
        this.avaNum = avaNum;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public List<TrdTradeFeeDetail> getTradeFeeList() {
        return tradeFeeList;
    }

    public void setTradeFeeList(List<TrdTradeFeeDetail> tradeFeeList) {
        this.tradeFeeList = tradeFeeList;
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
}

