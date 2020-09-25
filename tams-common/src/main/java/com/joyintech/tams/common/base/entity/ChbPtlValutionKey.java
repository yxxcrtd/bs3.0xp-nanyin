package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class ChbPtlValutionKey extends BaseEntity{
    /**
     * 日期
     */
    private Date cdate;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 日期
     * @return CDATE 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 日期
     * @param cdate 日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 组合代码
     * @return PORTFOLIO_ID 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 金融产品代码
     * @return FINPROD_ID 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 投资目的
     * @return INV_AIM 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }
}