package com.joyintech.tams.valuation.batch.exchangecarryover.dto;

import java.math.BigDecimal;

/**
 * 区间发生余额 Dto
 *  @author zhangwei
 * @createTime 2019-07-09 10:01
 */
public class InterBalanceDto {

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 原币
     */
    private String oCcy;

    /**
     * 区间原币发生余额_贷方
     */
    private BigDecimal cInterBalance;

    /**
     * 区间原币发生余额_借方
     */
    private BigDecimal dInterBalance;

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getoCcy() {
        return oCcy;
    }

    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    public BigDecimal getcInterBalance() {
        return cInterBalance;
    }

    public void setcInterBalance(BigDecimal cInterBalance) {
        this.cInterBalance = cInterBalance;
    }

    public BigDecimal getdInterBalance() {
        return dInterBalance;
    }

    public void setdInterBalance(BigDecimal dInterBalance) {
        this.dInterBalance = dInterBalance;
    }
}
