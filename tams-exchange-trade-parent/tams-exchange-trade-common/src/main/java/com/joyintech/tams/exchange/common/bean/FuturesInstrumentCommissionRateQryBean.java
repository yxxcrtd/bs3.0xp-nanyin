package com.joyintech.tams.exchange.common.bean;

/**
 * @author liujianping
 * @ClassName FuturesCommissionRateQryBean
 * @Description
 * @date 2020/11/20 10:12
 * @Version
 **/

public class FuturesInstrumentCommissionRateQryBean {
    private String accountId;
    private String stockCode;

    public FuturesInstrumentCommissionRateQryBean(String accountId, String stockCode) {
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
