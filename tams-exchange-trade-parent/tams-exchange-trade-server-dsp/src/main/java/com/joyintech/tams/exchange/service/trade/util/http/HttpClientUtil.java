package com.joyintech.tams.exchange.service.trade.util.http;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static final String COOKIE = "Cookie";
    private static final String QUESTION_MARK = "?";

    /**
     * 重试次数
     */
    private int retryCount = -1;
    /**
     * 连接池获取到连接的超时时间
     */
    private int connectionRequestTimeout = -1;
    /**
     * 建立连接的超时
     */
    private int connectTimeout = -1;
    /**
     * 获取数据的超时时间
     */
    private int socketTimeout = -1;

    private HttpMethods method = HttpMethods.POST;

    private HttpContentType contentType = HttpContentType.json;

    private String url;

    private Map<String, String> headers;

    private String charset = Charset.defaultCharset().displayName();

    private String context;

    private File file;

    public static HttpClientUtil create() {
        return new HttpClientUtil();
    }

    public HttpClientUtil retryCount(int retryCount) {
        if (this.retryCount > 0) {
            this.retryCount = retryCount;
        }
        return this;
    }

    public HttpClientUtil connectTimeout(int connectTimeout) {
        if (this.connectTimeout > 0) {
            this.connectTimeout = connectTimeout;
        }
        return this;
    }

    public HttpClientUtil connectionRequestTimeout(int connectionRequestTimeout) {
        if (this.connectionRequestTimeout > 0) {
            this.connectionRequestTimeout = connectionRequestTimeout;
        }
        return this;
    }

    public HttpClientUtil socketTimeout(int socketTimeout) {
        if (this.socketTimeout > 0) {
            this.socketTimeout = socketTimeout;
        }
        return this;
    }

    public HttpClientUtil method(HttpMethods method) {
        this.method = HttpMethods.GET;
        return this;
    }

    public HttpClientUtil method(String method) {
        this.method = HttpMethods.valueOf(method);
        if (this.method == null) {
            throw new RuntimeException("不支持HTTP" + method + "方法");
        }
        return this;
    }

    public HttpClientUtil charset(String charset) {
        if (!StringUtils.isEmpty(charset)) {
            this.charset = charset;
        }
        return this;
    }

    public HttpClientUtil get() {
        this.method = HttpMethods.GET;
        return this;
    }

    public HttpClientUtil post() {
        this.method = HttpMethods.POST;
        return this;
    }

    public HttpClientUtil url(String url) {
        this.url = url;
        return this;
    }

    public HttpClientUtil header(Map<String, String> headers) {
        if (null == this.headers) {
            this.headers = headers;
        } else {
            this.headers.putAll(headers);
        }
        return this;
    }

    public HttpClientUtil header(String name, String value) {
        if (null == this.headers) {
            this.headers = new HashMap<String, String>();
        }
        this.headers.put(name, value);
        return this;
    }

    public HttpClientUtil cookie(String cookieString) {
        if (null == this.headers) {
            this.headers = new HashMap<String, String>();
        }
        this.headers.put(COOKIE, cookieString);
        return this;
    }

    public HttpClientUtil cookie(String name, String value) {
        if (null == this.headers) {
            this.headers = new HashMap<String, String>();
        }
        String cookie = this.headers.get(COOKIE);
        if (null == cookie) {
            cookie = "";
        }
        cookie += name + "=" + value + ";";
        this.headers.put(COOKIE, cookie);
        return this;
    }

    public HttpClientUtil contentType(String contentType) {
        this.contentType = HttpContentType.valueOf(contentType);
        return this;
    }

    public HttpClientUtil json(String json) {
        this.contentType = HttpContentType.json;
        this.context = json;
        return this;
    }

    public HttpClientUtil json(Object object) {
        this.contentType = HttpContentType.json;
        this.context = JSON.toJSONString(object);
        return this;
    }

    public HttpClientUtil form(List<? extends NameValuePair> parameters) {
        this.contentType = HttpContentType.form;
        this.context = URLEncodedUtils.format(parameters, charset);
        return this;
    }

    public HttpClientUtil form(String formString) {
        this.contentType = HttpContentType.form;
        this.context = formString;
        return this;
    }

    public HttpClientUtil string(String context) {
        this.contentType = HttpContentType.string;
        this.context = context;
        return this;
    }

    public HttpClientUtil context(String context) {
        this.context = context;
        return this;
    }

    public HttpClientUtil file(File file) {
        this.contentType = HttpContentType.file;
        this.file = file;
        return this;
    }

    public HttpResult send() throws Exception {
        Builder builder = RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout);
        CloseableHttpClient client = HttpClientPool.getConnection(builder.build(), retryCount);
        HttpRequestBase request = null;
        if (StringUtils.equals(method.getName(), HttpMethods.GET.getName())) {
            //GET请求context内容拼接url
            request = method.newInstance(url + QUESTION_MARK + context);
        } else {
            request = method.newInstance(url);
        }
        if (null != headers && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
            HttpEntity entity = null;
            ContentType type = contentType.getContentType(charset, null);
            if (HttpContentType.string == contentType || HttpContentType.json == contentType || HttpContentType.form == contentType) {
                entity = new StringEntity(context, type);
            } else if (HttpContentType.file == contentType) {
                entity = new FileEntity(file, type);
            } else {
                throw new RuntimeException("暂不支持" + contentType + "类型的报文!");
            }
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            return new HttpResult(response, charset);
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

}
