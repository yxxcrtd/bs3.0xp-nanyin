package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDCustomerFinance {
    private String USER_CODE;//客户代码客户代码
    private String ACCOUNT;//
    private String CURRENCY;
    private String BLN_FLAG;

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public String getBLN_FLAG() {
        return BLN_FLAG;
    }

    public void setBLN_FLAG(String BLN_FLAG) {
        this.BLN_FLAG = BLN_FLAG;
    }
}
