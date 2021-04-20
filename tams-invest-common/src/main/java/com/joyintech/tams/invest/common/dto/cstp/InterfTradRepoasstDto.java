package com.joyintech.tams.invest.common.dto.cstp;

import com.joyintech.tams.common.base.entity.InterfTradRepoasst;

/**
 * @author zhangnian
 * @ClassName InterfTradRepoasstDto:抵押品信息
 * @description TODO
 * @date 2021/1/25 0025 19:14
 **/
public class InterfTradRepoasstDto extends InterfTradRepoasst {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 组合代码
     */
    private String portfolioId;

    private String ccy;

    /**
     * 登记托管机构
     */
    private String registOrg;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getRegistOrg() {
        return registOrg;
    }

    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}

