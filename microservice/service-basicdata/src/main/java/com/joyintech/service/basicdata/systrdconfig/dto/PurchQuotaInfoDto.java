package com.joyintech.service.basicdata.systrdconfig.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @Classname PurchQuotaInfoDto
 * @Description 新股申购、配股配债额度Dto
 * @author zhuhanlu 
 * @date 2020/12/14 13:37 
 * @version 1.0 
 */
public class PurchQuotaInfoDto extends BaseEntity {

    /**
     * 组合代码
     */
    private String portfolioCode;

    /**
     * 证券账户代码
     */
    private String secAcctId;


    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 业务类型
     */
    private String othCodeType;

    /**
     * 日期
     */
    private String date;

    /**
     * 额度
     */
    private BigDecimal quto;


    /**
     * 科创板额度
     */
    private BigDecimal ipoQuto;


    /**
     * 股东代码
     */
    private String stockHolderNo;


    /**
     * 交易场所
     */
    private String tradeMarket;


    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getOthCodeType() {
        return othCodeType;
    }

    public void setOthCodeType(String othCodeType) {
        this.othCodeType = othCodeType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getQuto() {
        return quto;
    }

    public void setQuto(BigDecimal quto) {
        this.quto = quto;
    }

    public BigDecimal getIpoQuto() {
        return ipoQuto;
    }

    public void setIpoQuto(BigDecimal ipoQuto) {
        this.ipoQuto = ipoQuto;
    }

    public String getStockHolderNo() {
        return stockHolderNo;
    }

    public void setStockHolderNo(String stockHolderNo) {
        this.stockHolderNo = stockHolderNo;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }
}
