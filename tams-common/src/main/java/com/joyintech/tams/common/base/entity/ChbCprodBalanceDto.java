package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_CPROD_BALANCE表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-11-05
 */
public class ChbCprodBalanceDto extends BaseEntity{
    /**
     * 主键
     */
    private String balId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 母产品代码
     */
    private String pProdId;

    /**
     * 期次代码
     */
    private String periodId;

    /**
     * 日期
     */
    private Date valueDate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 主键
     * @return BAL_ID 主键
     */
    public String getBalId() {
        return balId;
    }

    /**
     * 主键
     * @param balId 主键
     */
    public void setBalId(String balId) {
        this.balId = balId == null ? null : balId.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 母产品代码
     * @return P_PROD_ID 母产品代码
     */
    public String getpProdId() {
        return pProdId;
    }

    /**
     * 母产品代码
     * @param pProdId 母产品代码
     */
    public void setpProdId(String pProdId) {
        this.pProdId = pProdId == null ? null : pProdId.trim();
    }

    /**
     * 期次代码
     * @return PERIOD_ID 期次代码
     */
    public String getPeriodId() {
        return periodId;
    }

    /**
     * 期次代码
     * @param periodId 期次代码
     */
    public void setPeriodId(String periodId) {
        this.periodId = periodId == null ? null : periodId.trim();
    }

    /**
     * 日期
     * @return VALUE_DATE 日期
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 日期
     * @param valueDate 日期
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 到期日
     * @return MDATE 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 余额
     * @return BALANCE 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 余额
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 币种
     * @return CCY 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
}