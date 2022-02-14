package com.joyintech.tams.exchange.common.bean.investment;

/**
 * create By MAXX on 2020/12/21
 */
public class PtlAuthInfo {
    private String portfolioCode;//组合代码
    private String userId;//用户名

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
