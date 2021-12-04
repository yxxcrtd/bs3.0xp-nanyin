package com.joyintech.exchange.service.quote.bean;

/**
 * 接口平台 行情请求返回体
 *
 * @author swwan
 * @className InferfQuoteBean
 * @date 2021/10/29 14:38
 */
public class InterfQuoteBean {

    private String src;

    private String data;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
