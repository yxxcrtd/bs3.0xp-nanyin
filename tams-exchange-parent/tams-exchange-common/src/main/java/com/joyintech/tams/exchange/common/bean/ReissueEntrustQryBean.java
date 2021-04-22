package com.joyintech.tams.exchange.common.bean;

import java.util.List;

public class ReissueEntrustQryBean {
    private String accountId;

    List<ReissueEntrustBean> reissueEntrustBeans;

    private int pageNum; // 第几次

    private boolean isLast = false; // 是否最后一条

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<ReissueEntrustBean> getReissueEntrustBeans() {
        return reissueEntrustBeans;
    }

    public void setReissueEntrustBeans(List<ReissueEntrustBean> reissueEntrustBeans) {
        this.reissueEntrustBeans = reissueEntrustBeans;
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
