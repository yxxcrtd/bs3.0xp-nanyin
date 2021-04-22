package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName IntegratedBulkQuoteQryBean
 * @Description 大宗行情查询请求
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
public class IntegratedBulkQuoteQryBean {
    private String stockCode;
    private String entrustProp;
    private String entrustBs;
    private String accountId;
    private int pageSize;

    public IntegratedBulkQuoteQryBean(String stockCode, String entrustProp, String entrustBs, String accountId, int pageSize) {
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.entrustBs = entrustBs;
        this.accountId = accountId;
        this.pageSize = pageSize;
    }

    public IntegratedBulkQuoteQryBean() {
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
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
