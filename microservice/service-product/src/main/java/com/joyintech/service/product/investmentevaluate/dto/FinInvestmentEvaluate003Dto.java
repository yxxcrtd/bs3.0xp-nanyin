package com.joyintech.service.product.investmentevaluate.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: FinInvestmentEvaluate003Dto
 * @description: 组合信息
 * @author: jyl
 * @create: 2019-10-28 17:03
 **/
public class FinInvestmentEvaluate003Dto extends BaseEntity {
    private static final long serialVersionUID = -8291666227730929705L;
    private String portfolioId;//组合代码
    private String portfolioName;//组合名称
    private Date   effDate;//评价日（生效日）INDEX_ID
    private String evalIndex;//评价指标
    private BigDecimal indexId;//指标代码

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getEvalIndex() {
        return evalIndex;
    }

    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex;
    }

    public BigDecimal getIndexId() {
        return indexId;
    }

    public void setIndexId(BigDecimal indexId) {
        this.indexId = indexId;
    }
}
