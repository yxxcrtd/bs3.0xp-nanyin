package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDCancelEntrust {
    private String MARKET;
    private String ORDER_ID;
    private String ACCOUNT;
    private String MAC_ADDR;
    private String HD_ID;
    private String TRD_TERMCODE;
    private String SECU_CLS;
    private String USER_CODE;

    public String getMARKET() {
        return MARKET;
    }

    public void setMARKET(String MARKET) {
        this.MARKET = MARKET;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public String getMAC_ADDR() {
        return MAC_ADDR;
    }

    public void setMAC_ADDR(String MAC_ADDR) {
        this.MAC_ADDR = MAC_ADDR;
    }

    public String getHD_ID() {
        return HD_ID;
    }

    public void setHD_ID(String HD_ID) {
        this.HD_ID = HD_ID;
    }

    public String getTRD_TERMCODE() {
        return TRD_TERMCODE;
    }

    public void setTRD_TERMCODE(String TRD_TERMCODE) {
        this.TRD_TERMCODE = TRD_TERMCODE;
    }

    public String getSECU_CLS() {
        return SECU_CLS;
    }

    public void setSECU_CLS(String SECU_CLS) {
        this.SECU_CLS = SECU_CLS;
    }

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }
}
