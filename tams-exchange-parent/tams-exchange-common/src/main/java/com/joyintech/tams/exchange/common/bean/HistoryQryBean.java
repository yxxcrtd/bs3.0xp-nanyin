package com.joyintech.tams.exchange.common.bean;

public class HistoryQryBean {
    private String startDay;
    private String endDay;
    private String accountId;
    private String postStr;
    private int pageSize;

    public HistoryQryBean() {
    }

    public HistoryQryBean(String startDay, String endDay, String accountId, String postStr, int pageSize) {
        this.accountId = accountId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.postStr = postStr;
        this.pageSize = pageSize;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPostStr() {
        return postStr;
    }

    public void setPostStr(String postStr) {
        this.postStr = postStr;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
