package com.joyintech.tams.invest.product.product.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 风险指标 table
 */
public class FinProductEvaluate001Tab6Dto extends BaseEntity {
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
     * 波动率
     */
    private BigDecimal volatility;
    /**
     * 年化波动率
     */
    private BigDecimal calcYearVolatility;
    /**
     * 下行标准差
     */
    private BigDecimal calcDownStdDev;
    /**
     * 年化特累诺比率
     */
    private BigDecimal yearTerenoRatio;
    /**
     * 期间最大净值回撤
     */
    private BigDecimal periodMaximumReversion;
    /**
     * 历史最大净值回撤
     */
    private BigDecimal historyMaximumReversion;

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

    public BigDecimal getVolatility() {
        return volatility;
    }

    public void setVolatility(BigDecimal volatility) {
        //乘以100(%) 保留4位小数且四舍五入
        this.volatility = ArithmeticUtil.multiply(volatility,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCalcYearVolatility() {
        return calcYearVolatility;
    }

    public void setCalcYearVolatility(BigDecimal calcYearVolatility) {
        //乘以100(%) 保留4位小数且四舍五入
        this.calcYearVolatility = ArithmeticUtil.multiply(calcYearVolatility,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCalcDownStdDev() {
        return calcDownStdDev;
    }

    public void setCalcDownStdDev(BigDecimal calcDownStdDev) {
        this.calcDownStdDev = calcDownStdDev.setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getYearTerenoRatio() {
        return yearTerenoRatio;
    }

    public void setYearTerenoRatio(BigDecimal yearTerenoRatio) {
        //乘以100(%) 保留4位小数且四舍五入
        this.yearTerenoRatio = ArithmeticUtil.multiply(yearTerenoRatio,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getPeriodMaximumReversion() {
        return periodMaximumReversion;
    }

    public void setPeriodMaximumReversion(BigDecimal periodMaximumReversion) {
        this.periodMaximumReversion = periodMaximumReversion.setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getHistoryMaximumReversion() {
        return historyMaximumReversion;
    }

    public void setHistoryMaximumReversion(BigDecimal historyMaximumReversion) {
        this.historyMaximumReversion = historyMaximumReversion.setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
