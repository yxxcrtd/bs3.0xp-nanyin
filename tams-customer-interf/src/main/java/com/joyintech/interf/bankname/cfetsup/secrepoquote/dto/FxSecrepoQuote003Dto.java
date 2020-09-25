package com.joyintech.interf.bankname.cfetsup.secrepoquote.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 回购对话报价dto
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-08
 */
public class FxSecrepoQuote003Dto extends BaseEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 最新净价日期
     */
    private Date cdate;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 持有份额
     */
    private BigDecimal shareAmt;

    /**
     * 摊销总成本，券面+利息调整
     */
    private BigDecimal dscCostAmt;

    /**
     * 全价净值
     */
    private BigDecimal netprice;

    /**
     * 托管人
     */
    private String trustee;

    /**
     * 债券到期日
     */
    private Date mdate;

    /**
     *  本次质押券面总额（万元）
     */
    private BigDecimal repoAmt;

    /**
     * 已报价券面总额（万元）
     */
    private BigDecimal faceTotalAmt;

    /**
     * 委托券面总额（万元）
     */
    private BigDecimal trustTotalAmt;

    /**
     *  折算比例（%）
     */
    private BigDecimal convrate;


    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getDscCostAmt() {
        return dscCostAmt;
    }

    public void setDscCostAmt(BigDecimal dscCostAmt) {
        this.dscCostAmt = dscCostAmt;
    }

    public BigDecimal getNetprice() {
        return netprice;
    }

    public void setNetprice(BigDecimal netprice) {
        this.netprice = netprice;
    }

    public String getTrustee() {
        return trustee;
    }

    public void setTrustee(String trustee) {
        this.trustee = trustee;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getRepoAmt() {
        return repoAmt;
    }

    public void setRepoAmt(BigDecimal repoAmt) {
        this.repoAmt = repoAmt;
    }

    public BigDecimal getFaceTotalAmt() {
        return faceTotalAmt;
    }

    public void setFaceTotalAmt(BigDecimal faceTotalAmt) {
        this.faceTotalAmt = faceTotalAmt;
    }

    public BigDecimal getTrustTotalAmt() {
        return trustTotalAmt;
    }

    public void setTrustTotalAmt(BigDecimal trustTotalAmt) {
        this.trustTotalAmt = trustTotalAmt;
    }

    public BigDecimal getConvrate() {
        return convrate;
    }

    public void setConvrate(BigDecimal convrate) {
        this.convrate = convrate;
    }
}