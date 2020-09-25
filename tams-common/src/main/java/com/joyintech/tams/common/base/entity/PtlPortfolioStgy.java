package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class PtlPortfolioStgy extends BaseEntity {
    /**
     * 组合策略代码
     */
    private String ptlStgyId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 策略代码
     */
    private String strategyId;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 取得 组合策略代码
     * @return 组合策略代码
     */
    public String getPtlStgyId() {
        return ptlStgyId;
    }

    /**
     * 设置 组合策略代码
     * @param ptlStgyId 组合策略代码
     */
    public void setPtlStgyId(String ptlStgyId) {
        this.ptlStgyId = ptlStgyId == null ? null : ptlStgyId.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 策略代码
     * @return 策略代码
     */
    public String getStrategyId() {
        return strategyId;
    }

    /**
     * 设置 策略代码
     * @param strategyId 策略代码
     */
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
}