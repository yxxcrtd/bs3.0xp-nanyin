package com.joyintech.tams.exchange.common.db.domain;

import java.util.Date;

public class TerminalMapping {
    private String id;

    private String accountId;

    private String terminalName;

    private int terminalWeight;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName == null ? null : terminalName.trim();
    }

    public int getTerminalWeight() {
        return terminalWeight;
    }

    public void setTerminalWeight(int terminalWeight) {
        this.terminalWeight = terminalWeight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}