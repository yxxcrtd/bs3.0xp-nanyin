package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName IntegratedRepurchaseQuoteQryBean
 * @Description TODO
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
public class IntegratedRepurchaseQuoteQryBean {
    private int actionIn;
    private String entrustProp;
    private String accountId;
    private int pageSize;

    public IntegratedRepurchaseQuoteQryBean(int actionIn, String entrustProp, String accountId, int pageSize) {
        this.actionIn = actionIn;
        this.entrustProp = entrustProp;
        this.accountId = accountId;
        this.pageSize = pageSize;
    }

    public int getActionIn() {
        return actionIn;
    }

    public void setActionIn(int actionIn) {
        this.actionIn = actionIn;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
