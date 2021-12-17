package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName IntegratedFixedIncomeQuoteQryBean
 * @Description TODO
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
public class IntegratedFixedIncomeQuoteQryBean {
    private String stockCode;
    private String exchangeType;
    private String accountId;
    private int pageSize;

    public IntegratedFixedIncomeQuoteQryBean(String stockCode, String exchangeType, String accountId, int pageSize) {
        this.stockCode = stockCode;
        this.exchangeType = exchangeType;
        this.accountId = accountId;
        this.pageSize = pageSize;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
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
