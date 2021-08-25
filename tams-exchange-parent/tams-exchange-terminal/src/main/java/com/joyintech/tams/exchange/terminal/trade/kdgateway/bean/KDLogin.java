package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDLogin {
    private String USER_ID_CLS;
    private String USER_ID;
    private String USER_PWD;
    private String PROC_NAME;
    private String TERMINALINFO;

    public String getUSER_ID_CLS() {
        return USER_ID_CLS;
    }

    public void setUSER_ID_CLS(String USER_ID_CLS) {
        this.USER_ID_CLS = USER_ID_CLS;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_PWD() {
        return USER_PWD;
    }

    public void setUSER_PWD(String USER_PWD) {
        this.USER_PWD = USER_PWD;
    }

    public String getPROC_NAME() {
        return PROC_NAME;
    }

    public void setPROC_NAME(String PROC_NAME) {
        this.PROC_NAME = PROC_NAME;
    }

    public String getTERMINALINFO() {
        return TERMINALINFO;
    }

    public void setTERMINALINFO(String TERMINALINFO) {
        this.TERMINALINFO = TERMINALINFO;
    }
}
