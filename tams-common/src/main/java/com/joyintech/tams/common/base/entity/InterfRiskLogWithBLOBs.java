package com.joyintech.tams.common.base.entity;

public class InterfRiskLogWithBLOBs extends InterfRiskLog {
    /**
     * 请求数据
     */
    private String requestData;

    /**
     * 响应数据
     */
    private String responseData;

    /**
     * 取得 请求数据
     * @return 请求数据
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * 设置 请求数据
     * @param requestData 请求数据
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData == null ? null : requestData.trim();
    }

    /**
     * 取得 响应数据
     * @return 响应数据
     */
    public String getResponseData() {
        return responseData;
    }

    /**
     * 设置 响应数据
     * @param responseData 响应数据
     */
    public void setResponseData(String responseData) {
        this.responseData = responseData == null ? null : responseData.trim();
    }
}