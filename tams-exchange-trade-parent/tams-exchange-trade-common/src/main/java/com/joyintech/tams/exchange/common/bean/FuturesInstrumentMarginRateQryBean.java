package com.joyintech.tams.exchange.common.bean;

/**
 * @author liujianping
 * @ClassName FuturesInstrumentMarginRateQryBean
 * @Description
 * @date 2020/11/20 10:11
 * @Version
 **/

public class FuturesInstrumentMarginRateQryBean {
    private String accountId;
    private String stockCode;

    public FuturesInstrumentMarginRateQryBean(String accountId, String stockCode) {
        this.accountId = accountId;
        this.stockCode = stockCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
