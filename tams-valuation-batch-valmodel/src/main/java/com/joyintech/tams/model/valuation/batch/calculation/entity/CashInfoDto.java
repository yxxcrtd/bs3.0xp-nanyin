package com.joyintech.tams.model.valuation.batch.calculation.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 现金流
 * @author zznn
 * @date 2021/7/1
 */
public class CashInfoDto {

    /**
     * 理论起始日
     */
    private LocalDate theoryStartDate;

    /**
     * 理论截止日
     */
    private LocalDate theoryCutoffDate;

    /**
     * 实际起始日
     */
    private LocalDate actualStartDate;

    /**
     * 实际截止日
     */
    private LocalDate actualCutoffDate;

    /**
     * 现金流金额
     */
    private BigDecimal cashAmt;


    /**
     * 剩余期限
     */
    private BigDecimal remainingTerm;


    /**
     * 即期R值
     */
    private BigDecimal spR;

    public LocalDate getTheoryStartDate() {
        return theoryStartDate;
    }

    public void setTheoryStartDate(LocalDate theoryStartDate) {
        this.theoryStartDate = theoryStartDate;
    }

    public LocalDate getTheoryCutoffDate() {
        return theoryCutoffDate;
    }

    public void setTheoryCutoffDate(LocalDate theoryCutoffDate) {
        this.theoryCutoffDate = theoryCutoffDate;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDate getActualCutoffDate() {
        return actualCutoffDate;
    }

    public void setActualCutoffDate(LocalDate actualCutoffDate) {
        this.actualCutoffDate = actualCutoffDate;
    }

    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    public BigDecimal getRemainingTerm() {
        return remainingTerm;
    }

    public void setRemainingTerm(BigDecimal remainingTerm) {
        this.remainingTerm = remainingTerm;
    }

    public BigDecimal getSpR() {
        return spR;
    }

    public void setSpR(BigDecimal spR) {
        this.spR = spR;
    }
}

