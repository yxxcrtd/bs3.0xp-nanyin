package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.db.domain.Account;

import java.io.Serializable;

public class AccountLoginReqBean implements Serializable {
    private ServerExtendInfo serverExtendInfo;
    private Account account;

    public AccountLoginReqBean() {
    }

    public AccountLoginReqBean(ServerExtendInfo serverExtendInfo, Account account) {
        this.serverExtendInfo = serverExtendInfo;
        this.account = account;
    }

    public ServerExtendInfo getServerExtendInfo() {
        return serverExtendInfo;
    }

    public void setServerExtendInfo(ServerExtendInfo serverExtendInfo) {
        this.serverExtendInfo = serverExtendInfo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
