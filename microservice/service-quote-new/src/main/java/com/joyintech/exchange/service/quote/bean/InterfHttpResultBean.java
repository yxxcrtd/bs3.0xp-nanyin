package com.joyintech.exchange.service.quote.bean;

/**
 * 接口平台 http 请求返回体
 *
 * @author swwan
 * @date 2021/11/3 - 17:10
 */
public class InterfHttpResultBean {

    private String code;

    private String message;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
