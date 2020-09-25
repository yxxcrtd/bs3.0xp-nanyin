package com.joyintech.tams.invest.trade.investtrade.commanddispatcher.dto;

public class TrdCommandDispatcher004Dto {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 投资品种
     */
    private String finprodType;

    private String entrustDateStr;

    public String getEntrustDateStr() {
        return entrustDateStr;
    }

    public void setEntrustDateStr(String entrustDateStr) {
        this.entrustDateStr = entrustDateStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }
}
