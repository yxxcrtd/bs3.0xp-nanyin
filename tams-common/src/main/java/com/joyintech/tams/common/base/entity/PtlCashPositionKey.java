package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class PtlCashPositionKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 账号
     */
    private String acctId;

    /**
     * 持仓类型，可用、实际、在途等
     */
    private String hodingType;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 币种
     */
    private String ccy;

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
     * 取得 账号
     * @return 账号
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * 设置 账号
     * @param acctId 账号
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    /**
     * 取得 持仓类型，可用、实际、在途等
     * @return 持仓类型，可用、实际、在途等
     */
    public String getHodingType() {
        return hodingType;
    }

    /**
     * 设置 持仓类型，可用、实际、在途等
     * @param hodingType 持仓类型，可用、实际、在途等
     */
    public void setHodingType(String hodingType) {
        this.hodingType = hodingType == null ? null : hodingType.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
}