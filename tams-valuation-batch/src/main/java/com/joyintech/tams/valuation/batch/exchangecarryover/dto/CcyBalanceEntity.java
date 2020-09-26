package com.joyintech.tams.valuation.batch.exchangecarryover.dto;

import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;

import java.math.BigDecimal;

/**
 * 币种余额entity
 * @author zhangwei
 * @createTime 2019-07-10 09:24
 */
public class CcyBalanceEntity {

    /**
     * 未实现汇兑损益余额
     */
    private BigDecimal unRealBalance;

    /**
     * 未实现汇兑损益_余额方向
     */
    private String unRealBalFlag;

    public BigDecimal getUnRealBalance() {
        return unRealBalance;
    }

    public void setUnRealBalance(BigDecimal unRealBalance) {
        this.unRealBalance = unRealBalance;
    }

    public String getUnRealBalFlag() {
        return unRealBalFlag;
    }

    public void setUnRealBalFlag(String unRealBalFlag) {
        this.unRealBalFlag = unRealBalFlag;
    }

}
