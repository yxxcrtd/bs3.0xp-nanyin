package com.joyintech.tams.exchange.common.bean;

/**
 * @author liujianping
 * @ClassName FuturesPositionDetailQryBean
 * @Description
 * @date 2020/11/2 16:05
 * @Version
 **/

public class FuturesPositionDetailQryBean {
    private String accountId;
    private String stockCode;
    private int pageSize;//为0时不需要翻页，KDGateway没有翻页；其他接口必须翻页
    private String positionStr;

    public FuturesPositionDetailQryBean(String accountId, String stockCode, int pageSize, String positionStr) {
        this.accountId = accountId;
        this.stockCode = stockCode;
        this.pageSize = pageSize;
        this.positionStr = positionStr;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
}
