package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupTrialKey extends BaseEntity {
    /**
     * 业务编号，包括委托编号，交易编号等
     */
    private String tradeId;

    /**
     * 业务类型，模糊委托、预占信息、实占信息
     */
    private String tradeType;

    /**
     * 组合或产品代码
     */
    private String portfolioId;

    /**
     * 监督指标编号
     */
    private String indId;

    /**
     * 取得 业务编号，包括委托编号，交易编号等
     * @return 业务编号，包括委托编号，交易编号等
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号，包括委托编号，交易编号等
     * @param tradeId 业务编号，包括委托编号，交易编号等
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 业务类型，模糊委托、预占信息、实占信息
     * @return 业务类型，模糊委托、预占信息、实占信息
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 业务类型，模糊委托、预占信息、实占信息
     * @param tradeType 业务类型，模糊委托、预占信息、实占信息
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 组合或产品代码
     * @return 组合或产品代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合或产品代码
     * @param portfolioId 组合或产品代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 监督指标编号
     * @param indId 监督指标编号
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }
}