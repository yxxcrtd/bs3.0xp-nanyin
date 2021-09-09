package com.joyintech.tams.exchange.terminal.trade.kdgateway;

public class KDGatewayConnection {
    private String accountId;
    private String financeAccount;
    private String userCode;
    private String branchNo;
    private String session;
    private String szAccount;
    private String shAccount;
    private String tradeRecordNo;//记录数
    private String entrustRecordNo;//记录数
    private String terminalInfo;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSzAccount() {
        return szAccount;
    }

    public void setSzAccount(String szAccount) {
        this.szAccount = szAccount;
    }

    public String getShAccount() {
        return shAccount;
    }

    public void setShAccount(String shAccount) {
        this.shAccount = shAccount;
    }

    public String getTradeRecordNo() {
        return tradeRecordNo;
    }

    public void setTradeRecordNo(String tradeRecordNo) {
        this.tradeRecordNo = tradeRecordNo;
    }

    public String getEntrustRecordNo() {
        return entrustRecordNo;
    }

    public void setEntrustRecordNo(String entrustRecordNo) {
        this.entrustRecordNo = entrustRecordNo;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }
}
