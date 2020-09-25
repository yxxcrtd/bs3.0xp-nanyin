package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;
import java.util.Date;

public class S003CashPlanEntity {
    private  String CASH_TYPE;//    现金流类型
    private String VDATE;//  区间开始日
    private  String MDATE;//    区间结束日
    private String PAY_DATE;//    预计支付日
    private BigDecimal PRIN_AMT;//    计划支付本金（百元）
    private BigDecimal INT_AMT;//    计划支付利息（百元）
    private BigDecimal CASH_AMT;//    计划支付现金流（百元）

    public String getCASH_TYPE() {
        return CASH_TYPE;
    }

    public void setCASH_TYPE(String CASH_TYPE) {
        this.CASH_TYPE = CASH_TYPE;
    }

    public String getVDATE() {
        return VDATE;
    }

    public void setVDATE(String VDATE) {
        this.VDATE = VDATE;
    }

    public String getMDATE() {
        return MDATE;
    }

    public void setMDATE(String MDATE) {
        this.MDATE = MDATE;
    }

    public String getPAY_DATE() {
        return PAY_DATE;
    }

    public void setPAY_DATE(String PAY_DATE) {
        this.PAY_DATE = PAY_DATE;
    }

    public BigDecimal getPRIN_AMT() {
        return PRIN_AMT;
    }

    public void setPRIN_AMT(BigDecimal PRIN_AMT) {
        this.PRIN_AMT = PRIN_AMT;
    }

    public BigDecimal getINT_AMT() {
        return INT_AMT;
    }

    public void setINT_AMT(BigDecimal INT_AMT) {
        this.INT_AMT = INT_AMT;
    }

    public BigDecimal getCASH_AMT() {
        return CASH_AMT;
    }

    public void setCASH_AMT(BigDecimal CASH_AMT) {
        this.CASH_AMT = CASH_AMT;
    }
}
