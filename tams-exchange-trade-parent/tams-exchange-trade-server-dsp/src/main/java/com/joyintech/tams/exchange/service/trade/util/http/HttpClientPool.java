package com.joyintech.tams.exchange.service.trade.util.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http Client Pool
 *
 * @author Zim
 */
public class HttpClientPool {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientPool.class);

    private static PoolingHttpClientConnectionManager pool;

    public static synchronized void init(int size) throws Exception {
        logger.info("初始化Http Client Pool...");
//        SSLContextBuilder builder = new SSLContextBuilder();
//        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
        // 配置同时支持 HTTP 和 HTPPS
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
//        		                                                                 .register("https", sslsf)
                .build();
        pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 同时最多连接数
        pool.setMaxTotal(size);
        // 设置最大路由
        pool.setDefaultMaxPerRoute(size);
        // 此处解释下MaxtTotal和DefaultMaxPerRoute的区别：
        // 1、MaxtTotal是整个池子的大小；
        // 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如：
        // MaxtTotal=400 DefaultMaxPerRoute=200
        // 而我只连接到http://www.abc.com时，到这个主机的并发最多只有200；而不是400；
        // 而我连接到http://www.bac.com 和
        // http://www.ccd.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute
        logger.info("初始化Http Client Pool完成,最大连接数:{}", size);
    }

    /**
     * 获取http连接
     *
     * @param config     请求配置
     * @param retryCount 重试次数
     * @return
     */
    public static CloseableHttpClient getConnection(RequestConfig config, int retryCount) {
        HttpClientBuilder builder = HttpClients.custom()
                .setConnectionManager(pool)
                .setDefaultRequestConfig(config);
//		if (null!=cookieStore) {
//			builder.setDefaultCookieStore(cookieStore);
//		}
        //重试策略
        if (retryCount > 0) {
            builder.setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, false));
        } else {
            builder.disableAutomaticRetries();
        }
        return builder.build();
    }

    public static PoolingHttpClientConnectionManager getPool() {
        return pool;
    }

}
