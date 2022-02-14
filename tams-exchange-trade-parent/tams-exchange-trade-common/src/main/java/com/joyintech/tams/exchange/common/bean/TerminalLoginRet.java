package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.db.domain.Account;

import java.util.List;
import java.util.Map;

public class TerminalLoginRet {
    private Map<ServerExtendInfo, List<Account>> accountMap;

    public Map<ServerExtendInfo, List<Account>> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<ServerExtendInfo, List<Account>> accountMap) {
        this.accountMap = accountMap;
    }
}
