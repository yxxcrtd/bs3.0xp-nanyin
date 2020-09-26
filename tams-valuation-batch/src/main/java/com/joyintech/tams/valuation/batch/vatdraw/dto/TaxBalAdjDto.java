/*
 * 文件名：AddTaxRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 差额调整Dto
 * @author weipeng
 * @version 1.0 2018年8月7日
 * @see TaxBalAdjDto
 * @since 1.0
 */
public class TaxBalAdjDto {

    /**
     * 原币差额
     */
    private BigDecimal taxBal;

    /**
     * 本币差额，含税销售额本币差额
     */
    private BigDecimal taxBalB;

    /**
     * 含税销售额本币差额
     */
    private BigDecimal incomeBalB;

    /**
     * Gets the value of taxBal.
     *
     * @return the value of taxBal
     */
    public BigDecimal getTaxBal() {
        return taxBal;
    }

    /**
     * Sets the taxBal.
     * <p>You can use getTaxBal() to get the value of taxBal</p>
     *
     * @param taxBal taxBal
     */
    public void setTaxBal(BigDecimal taxBal) {
        this.taxBal = taxBal;
    }

    /**
     * Gets the value of taxBalB.
     *
     * @return the value of taxBalB
     */
    public BigDecimal getTaxBalB() {
        return taxBalB;
    }

    /**
     * Sets the taxBalB.
     * <p>You can use getTaxBalB() to get the value of taxBalB</p>
     *
     * @param taxBalB taxBalB
     */
    public void setTaxBalB(BigDecimal taxBalB) {
        this.taxBalB = taxBalB;
    }

    /**
     * Gets the value of incomeBalB.
     *
     * @return the value of incomeBalB
     */
    public BigDecimal getIncomeBalB() {
        return incomeBalB;
    }

    /**
     * Sets the incomeBalB.
     * <p>You can use getIncomeBalB() to get the value of incomeBalB</p>
     *
     * @param incomeBalB incomeBalB
     */
    public void setIncomeBalB(BigDecimal incomeBalB) {
        this.incomeBalB = incomeBalB;
    }
}
