package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class PtlPortfolio extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    private String portfolioType;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 组合开始日
     */
    private Date vdate;

    /**
     * 组合结束日
     */
    private Date mdate;

    /**
     * 收益类型，预期收益型/净值型/货币型
     */
    private String profitType;

    /**
     * 是否保本
     */
    private String incomeFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 母组合代码
     */
    private String parentPortfolioId;

    /**
     * 是否能投资
     */
    private String investYesorno;

    /**
     * 是否启用风险
     */
    private String riskEnabled;

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
     * 取得 组合类型，管理组合、投资组合、资金单元、过渡组合
     * @return 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    public String getPortfolioType() {
        return portfolioType;
    }

    /**
     * 设置 组合类型，管理组合、投资组合、资金单元、过渡组合
     * @param portfolioType 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType == null ? null : portfolioType.trim();
    }

    /**
     * 取得 组合名称
     * @return 组合名称
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 组合名称
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
    }

    /**
     * 取得 组合开始日
     * @return 组合开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 组合开始日
     * @param vdate 组合开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 组合结束日
     * @return 组合结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 组合结束日
     * @param mdate 组合结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 收益类型，预期收益型/净值型/货币型
     * @return 收益类型，预期收益型/净值型/货币型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型，预期收益型/净值型/货币型
     * @param profitType 收益类型，预期收益型/净值型/货币型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 是否保本
     * @return 是否保本
     */
    public String getIncomeFlag() {
        return incomeFlag;
    }

    /**
     * 设置 是否保本
     * @param incomeFlag 是否保本
     */
    public void setIncomeFlag(String incomeFlag) {
        this.incomeFlag = incomeFlag == null ? null : incomeFlag.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 母组合代码
     * @return 母组合代码
     */
    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    /**
     * 设置 母组合代码
     * @param parentPortfolioId 母组合代码
     */
    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId == null ? null : parentPortfolioId.trim();
    }

    /**
     * 取得 是否能投资
     * @return 是否能投资
     */
    public String getInvestYesorno() {
        return investYesorno;
    }

    /**
     * 设置 是否能投资
     * @param investYesorno 是否能投资
     */
    public void setInvestYesorno(String investYesorno) {
        this.investYesorno = investYesorno == null ? null : investYesorno.trim();
    }

    /**
     * 取得 是否启用风险
     * @return 是否启用风险
     */
    public String getRiskEnabled() {
        return riskEnabled;
    }

    /**
     * 设置 是否启用风险
     * @param riskEnabled 是否启用风险
     */
    public void setRiskEnabled(String riskEnabled) {
        this.riskEnabled = riskEnabled == null ? null : riskEnabled.trim();
    }
}