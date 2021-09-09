package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDEnableQryBean {
    private String USER_CODE;
    private String MARKET;
    private String ACCOUNT;
    private String SECU_ACC;
    private String SECU_CODE;
    private String TRD_ID;
    private String PRICE;

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getMARKET() {
        return MARKET;
    }

    public void setMARKET(String MARKET) {
        this.MARKET = MARKET;
    }

    public String getSECU_ACC() {
        return SECU_ACC;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public void setSECU_ACC(String SECU_ACC) {
        this.SECU_ACC = SECU_ACC;
    }

    public String getSECU_CODE() {
        return SECU_CODE;
    }

    public void setSECU_CODE(String SECU_CODE) {
        this.SECU_CODE = SECU_CODE;
    }

    public String getTRD_ID() {
        return TRD_ID;
    }

    public void setTRD_ID(String TRD_ID) {
        this.TRD_ID = TRD_ID;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }
}
