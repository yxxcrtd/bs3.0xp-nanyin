package com.joyintech.tams.exchange.service.trade.util.http;

import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpResult {
    /**
     * 执行结果-body
     */
    private String result;
    /**
     * 状态码-statusCode
     */
    private int statusCode;
    /**
     * 状态行-StatusLine
     */
    private StatusLine statusLine;
    /**
     * 响应头信息
     */
    private Header[] responseHeaders;
    /**
     * 协议版本
     */
    private ProtocolVersion protocolVersion;

    public HttpResult(CloseableHttpResponse response, String charset) throws Exception {
        // 协议版本
        this.protocolVersion = response.getProtocolVersion();
        // 状态行-StatusLine
        this.statusLine = response.getStatusLine();
        // 状态码-statusCode
        this.statusCode = this.statusLine.getStatusCode();
        // 响应头信息
        this.responseHeaders = response.getAllHeaders();
        if (response.getEntity() != null) {
            // 按指定编码转换结果实体为String类型
            this.result = EntityUtils.toString(response.getEntity(), charset);
        } else {//有可能是head请求
            this.result = response.getStatusLine().toString();
        }
        EntityUtils.consume(response.getEntity());
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
}
