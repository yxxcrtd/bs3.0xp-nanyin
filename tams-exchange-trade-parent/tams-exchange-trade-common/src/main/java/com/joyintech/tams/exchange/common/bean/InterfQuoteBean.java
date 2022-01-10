package com.joyintech.tams.exchange.common.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 接口平台 行情请求返回体
 *
 * @author swwan
 * @className InferfQuoteBean
 * @date 2021/10/29 14:38
 */
public class InterfQuoteBean {

    private String src;

    private JSONObject data;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
