package com.joyintech.tams.invest.product.investmentevaluate.dto;

import java.util.Date;

/**
 * @program: FinInvestmentEvaluate002Dto
 * @description: 金融产品信息+组合代码名称+开始日结束日
 * @author: jyl
 * @create: 2019-10-28 10:24
 **/
public class FinInvestmentEvaluate002Dto {
    private String portfolioId;//组合代码
    private String portfolioName;//组合名称
    private String finprodId; // 金融产品代码
    private String branch; // 分支序号
    private String finprodType2;//金融产品类型（投管）产品大类
    private String profitType;//收益类型
    private String dealMode;//处理模式
    private Date vdate;//起息日
    private Date startDate;//区间开始日（默认取查询条件的起始日）
    private Date endDate;//区间结束日

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

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
