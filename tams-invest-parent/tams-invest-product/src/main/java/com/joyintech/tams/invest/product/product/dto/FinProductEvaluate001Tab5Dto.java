package com.joyintech.tams.invest.product.product.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收益结构指标 table
 */
public class FinProductEvaluate001Tab5Dto extends BaseEntity {
    /**
     * 产品代码
     */
    private String finprodId;
    /**
     * 产品名称
     */
    private String finprodName;
    /**
     * 评价日
     */
    private Date mdate;
    /**
     * 子组合代码
     */
    private String subPortfolioId;
    /**
     * 子组合名称
     */
    private String subPortfolioName;
    /**
     * 子组合区间收益目标
     */
    private BigDecimal cportfolioId;
    /**
     * 子组合区间年化收益率
     */
    private BigDecimal sectionAnnualYield;
    /**
     * 子组合收益贡献率
     */
    private BigDecimal contribution;
    /**
     * 子组合目标收益贡献率
     */
    private BigDecimal targetContribution;

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

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getSubPortfolioId() {
        return subPortfolioId;
    }

    public void setSubPortfolioId(String subPortfolioId) {
        this.subPortfolioId = subPortfolioId;
    }

    public String getSubPortfolioName() {
        return subPortfolioName;
    }

    public void setSubPortfolioName(String subPortfolioName) {
        this.subPortfolioName = subPortfolioName;
    }

    public BigDecimal getCportfolioId() {
        return cportfolioId;
    }

    public void setCportfolioId(BigDecimal cportfolioId) {
        //乘以100(%) 保留4位小数且四舍五入
        this.cportfolioId = ArithmeticUtil.multiply(cportfolioId,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getSectionAnnualYield() {
        return sectionAnnualYield;
    }

    public void setSectionAnnualYield(BigDecimal sectionAnnualYield) {
        //乘以100(%) 保留4位小数且四舍五入
        this.sectionAnnualYield = ArithmeticUtil.multiply(sectionAnnualYield,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getContribution() {
        return contribution;
    }

    public void setContribution(BigDecimal contribution) {
        //乘以100(%) 保留4位小数且四舍五入
        this.contribution = ArithmeticUtil.multiply(contribution,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTargetContribution() {
        return targetContribution;
    }

    public void setTargetContribution(BigDecimal targetContribution) {
        //乘以100(%) 保留4位小数且四舍五入
        this.targetContribution = ArithmeticUtil.multiply(targetContribution,BigDecimal.valueOf(100)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
