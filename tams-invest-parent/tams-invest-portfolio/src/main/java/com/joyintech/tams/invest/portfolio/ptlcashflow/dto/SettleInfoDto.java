package com.joyintech.tams.invest.portfolio.ptlcashflow.dto;

import java.math.BigDecimal;

public class SettleInfoDto {
    /**
     * 收款账户
     */
    private String REC_ACCT_NO;
    /**
     * 付款账户
     */
    private String PAY_ACCT_NO;
    /**
     * 划款金额
     */
    private BigDecimal SETTLE_AMT;

    /**
     * 币种
     */
    private String CCY;

    public String getREC_ACCT_NO() {
        return REC_ACCT_NO;
    }

    public void setREC_ACCT_NO(String REC_ACCT_NO) {
        this.REC_ACCT_NO = REC_ACCT_NO;
    }

    public String getPAY_ACCT_NO() {
        return PAY_ACCT_NO;
    }

    public void setPAY_ACCT_NO(String PAY_ACCT_NO) {
        this.PAY_ACCT_NO = PAY_ACCT_NO;
    }

    public BigDecimal getSETTLE_AMT() {
        return SETTLE_AMT;
    }

    public void setSETTLE_AMT(BigDecimal SETTLE_AMT) {
        this.SETTLE_AMT = SETTLE_AMT;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }
}
