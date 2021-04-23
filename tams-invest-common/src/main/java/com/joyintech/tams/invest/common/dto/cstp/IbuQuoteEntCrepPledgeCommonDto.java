package com.joyintech.tams.invest.common.dto.cstp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 质押式回购报价质押券传出dto
 * @auther zhangnian
 * @date 2021/3/15
 */
public class IbuQuoteEntCrepPledgeCommonDto {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -302695451253888578L;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 质押面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeFaceValue;

    /**
     * 质押数量
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pledgeNumber;

    /**
     * 质押率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeRatio;

    /**
     * 质押金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeAmt;

    /**
     * 单位净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 净值日期
     */
    private Date cpriceDate;

    /**
     * 债券代码
     */
    private String bondCode;

    /**
     * 登记托管机构
     */
    private String repRegistOrg;

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public BigDecimal getPledgeFaceValue() {
        return pledgeFaceValue;
    }

    public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
        this.pledgeFaceValue = pledgeFaceValue;
    }

    public BigDecimal getPledgeNumber() {
        return pledgeNumber;
    }

    public void setPledgeNumber(BigDecimal pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }

    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public Date getCpriceDate() {
        return cpriceDate;
    }

    public void setCpriceDate(Date cpriceDate) {
        this.cpriceDate = cpriceDate;
    }

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public String getRepRegistOrg() {
        return repRegistOrg;
    }

    public void setRepRegistOrg(String repRegistOrg) {
        this.repRegistOrg = repRegistOrg;
    }
}

