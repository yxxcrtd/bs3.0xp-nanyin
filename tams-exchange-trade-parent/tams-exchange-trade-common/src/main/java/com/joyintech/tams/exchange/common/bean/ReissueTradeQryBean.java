package com.joyintech.tams.exchange.common.bean;

import java.util.List;

public class ReissueTradeQryBean {
    private String accountId;

    private List<ReissueTradeBean> reissueTradeBeans;

    private int pageNum; // 第几次

    private boolean isLast = false;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<ReissueTradeBean> getReissueTradeBeans() {
        return reissueTradeBeans;
    }

    public void setReissueTradeBeans(List<ReissueTradeBean> reissueTradeBeans) {
        this.reissueTradeBeans = reissueTradeBeans;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
