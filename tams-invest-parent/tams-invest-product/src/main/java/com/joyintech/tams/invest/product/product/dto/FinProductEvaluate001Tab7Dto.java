package com.joyintech.tams.invest.product.product.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;

import java.math.BigDecimal;
import java.util.Date;

public class FinProductEvaluate001Tab7Dto extends BaseEntity {
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
     * 年化夏普比率
     */
    private BigDecimal yearSharpRatio;
    /**
     * 年化卡玛比率
     */
    private BigDecimal yearKamaRatio;
    /**
     * 年化索提诺比率
     */
    private BigDecimal calcSortinoRatio;
    /**
     * 詹森指数
     */
    private BigDecimal calcJensenIndex;

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

    public BigDecimal getYearSharpRatio() {
        return yearSharpRatio;
    }

    public void setYearSharpRatio(BigDecimal yearSharpRatio) {
        //乘以100(%) 保留4位小数且四舍五入
        this.yearSharpRatio = ArithmeticUtil.multiply(yearSharpRatio,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getYearKamaRatio() {
        return yearKamaRatio;
    }

    public void setYearKamaRatio(BigDecimal yearKamaRatio) {
        //乘以100(%) 保留4位小数且四舍五入
        this.yearKamaRatio = ArithmeticUtil.multiply(yearKamaRatio,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCalcSortinoRatio() {
        return calcSortinoRatio;
    }

    public void setCalcSortinoRatio(BigDecimal calcSortinoRatio) {
        //乘以100(%) 保留4位小数且四舍五入
        this.calcSortinoRatio = ArithmeticUtil.multiply(calcSortinoRatio,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCalcJensenIndex() {
        return calcJensenIndex;
    }

    public void setCalcJensenIndex(BigDecimal calcJensenIndex) {
        this.calcJensenIndex = calcJensenIndex.setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
