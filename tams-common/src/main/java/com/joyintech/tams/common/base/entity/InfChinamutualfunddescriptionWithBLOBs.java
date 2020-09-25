package com.joyintech.tams.common.base.entity;

public class InfChinamutualfunddescriptionWithBLOBs extends InfChinamutualfunddescription {
    /**
     * 投资策略
     */
    private String investstrategy;

    /**
     * 基金风险收益特征
     */
    private String riskReturn;

    /**
     * 取得 投资策略
     * @return 投资策略
     */
    public String getInveststrategy() {
        return investstrategy;
    }

    /**
     * 设置 投资策略
     * @param investstrategy 投资策略
     */
    public void setInveststrategy(String investstrategy) {
        this.investstrategy = investstrategy == null ? null : investstrategy.trim();
    }

    /**
     * 取得 基金风险收益特征
     * @return 基金风险收益特征
     */
    public String getRiskReturn() {
        return riskReturn;
    }

    /**
     * 设置 基金风险收益特征
     * @param riskReturn 基金风险收益特征
     */
    public void setRiskReturn(String riskReturn) {
        this.riskReturn = riskReturn == null ? null : riskReturn.trim();
    }
}