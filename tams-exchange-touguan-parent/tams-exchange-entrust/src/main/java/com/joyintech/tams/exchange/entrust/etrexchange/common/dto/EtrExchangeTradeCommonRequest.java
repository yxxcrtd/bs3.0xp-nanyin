package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="查询计算数据共通请求参数",description="查询计算数据共通请求参数")
public class EtrExchangeTradeCommonRequest {

    /**
     * 资产代码
     */
    @ApiModelProperty(name="finprodId",value="资产代码")
    private String finprodId;

    /**
     * 组合代码
     */
    @ApiModelProperty(name="portfolioId",value="组合代码")
    private String portfolioId;

    /**
     * 市场代码
     */
    @ApiModelProperty(name="tradeMarket",value="资产代码")
    private String tradeMarket;

    /**
     * 资产代码
     */
    @ApiModelProperty(name="finprodType2",value="资产类型")
    private String finprodType2;

    /**
     * 资产代码
     */
    @ApiModelProperty(name="ps",value="委托方向")
    private String ps;

    /**
     * 证券账户
     */
    @ApiModelProperty(name="secAcctId",value="证券账户")
    private String secAcctId;

    /**
     * 交易时间
     */
    @ApiModelProperty(name="cDate",value="交易日期")
    private String cDate;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
