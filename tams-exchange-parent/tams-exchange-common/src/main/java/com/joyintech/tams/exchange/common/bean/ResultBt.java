package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

/**
 * create By MAXX on 2020/9/30
 */
public class ResultBt implements Serializable {
    private String errorNo;//错误代码
    private String errorInfo;//错误原因

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
