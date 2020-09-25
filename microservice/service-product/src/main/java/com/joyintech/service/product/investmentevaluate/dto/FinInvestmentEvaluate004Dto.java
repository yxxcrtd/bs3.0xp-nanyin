package com.joyintech.service.product.investmentevaluate.dto;

import java.math.BigDecimal;

/**
 * @program: FinInvestmentEvaluate004Dto
 * @description: 组合资产估值信息列表
 * @author: jyl
 * @create: 2019-10-30 19:09
 **/
public class FinInvestmentEvaluate004Dto {
    BigDecimal bonds;//持仓
    BigDecimal tdyFloatIngpl;//公允价值变动
    BigDecimal costAmt;//成本

    public BigDecimal getBonds() {
        return bonds;
    }

    public void setBonds(BigDecimal bonds) {
        this.bonds = bonds;
    }

    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    public BigDecimal getCostAmt() {
        return costAmt;
    }

    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }
}
