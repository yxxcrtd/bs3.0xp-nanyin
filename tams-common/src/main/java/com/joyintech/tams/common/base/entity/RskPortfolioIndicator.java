package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RskPortfolioIndicator extends RskPortfolioIndicatorKey {
    /**
     * 组合层次(单组合,组合包)
     */
    private String portfolioLevel;

    /**
     * 有效区间开始
     */
    private Date startDate;

    /**
     * 有效区间结束
     */
    private Date endDate;

    /**
     * 取得 组合层次(单组合,组合包)
     * @return 组合层次(单组合,组合包)
     */
    public String getPortfolioLevel() {
        return portfolioLevel;
    }

    /**
     * 设置 组合层次(单组合,组合包)
     * @param portfolioLevel 组合层次(单组合,组合包)
     */
    public void setPortfolioLevel(String portfolioLevel) {
        this.portfolioLevel = portfolioLevel == null ? null : portfolioLevel.trim();
    }

    /**
     * 取得 有效区间开始
     * @return 有效区间开始
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 有效区间开始
     * @param startDate 有效区间开始
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 有效区间结束
     * @return 有效区间结束
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 有效区间结束
     * @param endDate 有效区间结束
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}