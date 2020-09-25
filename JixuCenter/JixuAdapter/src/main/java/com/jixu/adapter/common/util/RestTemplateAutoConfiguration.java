/*
 * 文件名：RestTemplateAutoConfiguration.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.jixu.adapter.common.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import com.joyintech.framework.http.ClientHttpRequestHandler;
import com.joyintech.framework.util.JsonUtils;

/**
 * RestTemplate自动配置
 * @author 何阳阳
 * @version 1.0 2019年1月23日
 * @see RestTemplateAutoConfiguration
 * @since 1.0
 */
@Configuration
public class RestTemplateAutoConfiguration {

    @Autowired(required = false)
    private List<ClientHttpRequestHandler> handlers;

    @Bean("simpleRestTemplate")
    public JixuRestTemplate localRestTemplate() {
        return buildRestTemplate();
    }

    @Primary
    @Bean("loadBalancedRestTemplate")
    @LoadBalanced
    public JixuRestTemplate restTemplate() {
        return buildRestTemplate();
    }
    
    @Bean("asyncRestTemplate")
    @LoadBalanced
    public AsyncRestTemplate asyncRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //设置链接超时时间
        factory.setConnectTimeout(100);
        //设置读取资料超时时间
        factory.setReadTimeout(200);
        //设置异步任务（线程不会重用，每次调用时都会重新启动一个新的线程）
        factory.setTaskExecutor(new SimpleAsyncTaskExecutor());
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(factory);
        asyncRestTemplate.getInterceptors().add(new AsyncClientHttpRequestInterceptor() {
            @Override
            public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request, byte[] body,
                                                                  AsyncClientHttpRequestExecution execution)
                throws IOException {

                if(null != handlers) {
                    handlers.forEach(handler -> handler.preRequest(request));
                }
                return execution.executeAsync(request, body);
            }
        });
        return asyncRestTemplate;
    }
    
    private JixuRestTemplate buildRestTemplate() {
        JixuRestTemplate restTemplate = new JixuRestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverterList = restTemplate.getMessageConverters();
        httpMessageConverterList.forEach(converter -> {
            if(converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter)converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
            else if(converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter JacksonConverter = (MappingJackson2HttpMessageConverter)converter;
                JacksonConverter.setObjectMapper(JsonUtils.OBJECT_MAPPER);
                JacksonConverter.setDefaultCharset(StandardCharsets.UTF_8);
            }
        });
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                                ClientHttpRequestExecution execution)
                throws IOException {
                if(null != handlers) {
                    handlers.forEach(handler -> handler.preRequest(request));
                }
                ClientHttpResponse response = execution.execute(request, body);
                if(null != handlers) {
                    handlers.forEach(handler -> handler.afterResponse(request, response));
                }
                return response;
            }
        });
        return restTemplate;
    }
}
