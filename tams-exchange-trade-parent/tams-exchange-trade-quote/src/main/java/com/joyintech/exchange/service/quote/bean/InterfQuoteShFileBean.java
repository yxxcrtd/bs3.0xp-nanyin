package com.joyintech.exchange.service.quote.bean;

/**
 * 上海文件行情数据体
 *
 * @author swwan
 * @date 2021/11/4 - 15:32
 */
public class InterfQuoteShFileBean {

    /**
     * 竞价行情数据
     */
    private String mktdt;

    /**
     * 涨跌停价数据
     */
    private String cpxx;

    public String getMktdt() {
        return mktdt;
    }

    public void setMktdt(String mktdt) {
        this.mktdt = mktdt;
    }

    public String getCpxx() {
        return cpxx;
    }

    public void setCpxx(String cpxx) {
        this.cpxx = cpxx;
    }
}
