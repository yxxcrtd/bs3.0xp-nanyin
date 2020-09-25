package com.joyintech.tams.invest.product.product.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收益指标 table
 */
public class FinProductEvaluate001Tab4Dto extends BaseEntity {
    /**
     * 产品代码
     */
    private String finprodId;
    /**
     * 产品名称
     */
    private String finprodName;
    /**
     * 区间开始日
     */
    private Date accrStrDate;
    /**
     * 区间结束日
     */
    private Date accrEndDate;
    /**
     * 年化收益率
     */
    private BigDecimal yearYieldDec;
    /**
     * 净值增长率
     */
    private BigDecimal rate;
    /**
     * 胜率
     */
    private BigDecimal winRate;
    /**
     * 正负收益周期比
     */
    private BigDecimal periodRadio;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public Date getAccrStrDate() {
        return accrStrDate;
    }

    public void setAccrStrDate(Date accrStrDate) {
        this.accrStrDate = accrStrDate;
    }

    public Date getAccrEndDate() {
        return accrEndDate;
    }

    public void setAccrEndDate(Date accrEndDate) {
        this.accrEndDate = accrEndDate;
    }

    public BigDecimal getYearYieldDec() {
        return yearYieldDec;
    }

    public void setYearYieldDec(BigDecimal yearYieldDec) {
        //乘以100(%) 保留4位小数且四舍五入
        this.yearYieldDec = ArithmeticUtil.multiply(yearYieldDec,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        //乘以100(%) 保留4位小数且四舍五入
        this.rate = ArithmeticUtil.multiply(rate,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getWinRate() {
        return winRate;
    }

    public void setWinRate(BigDecimal winRate) {
        //乘以100(%) 保留4位小数且四舍五入
        this.winRate = ArithmeticUtil.multiply(winRate,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getPeriodRadio() {
        return periodRadio;
    }

    public void setPeriodRadio(BigDecimal periodRadio) {
        //乘以100(%) 保留4位小数且四舍五入
        this.periodRadio = ArithmeticUtil.multiply(periodRadio,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
