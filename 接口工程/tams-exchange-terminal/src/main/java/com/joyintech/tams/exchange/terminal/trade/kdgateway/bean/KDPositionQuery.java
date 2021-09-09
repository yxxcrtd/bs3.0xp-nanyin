package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDPositionQuery {
    private String USER_CODE;
    private String SECU_CODE;

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getSECU_CODE() {
        return SECU_CODE;
    }

    public void setSECU_CODE(String SECU_CODE) {
        this.SECU_CODE = SECU_CODE;
    }
}
