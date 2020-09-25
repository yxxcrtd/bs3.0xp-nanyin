/*
 * 文件名：ServiceApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年7月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.jixu.common.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.jixu.common.util.ServiceApplication.AutoConfig.ContextPathConfiguration;

/**
 * 微服务访问路径定义
 * @author wangqidong
 * @version 1.0 2019年7月24日
 * @see ServiceApplication
 * @since 1.0
 */
@Configuration("AdapterServiceApplication")
public class ServiceApplication {

    //适配器服务
    public static String ADAPTER_SERVICE_NAME = "http://adapterService";
    
    //接口服务
    public static String CUSTOMER_INTERF_NAME = "http://customerInterfService";
    
    //下游资产服务
    public static String ASSET_SERVICE_NAME = "http://assetService";
    
    @Configuration
    @ConditionalOnProperty(prefix = "application.micorservice.contextPath", name = "customerInterfService")
    @EnableConfigurationProperties(ContextPathConfiguration.class)
    class AutoConfig {

        @Autowired
        private ContextPathConfiguration config;

        @PostConstruct
        private void setConfig() {
            if(!config.isSingle()) {
                ADAPTER_SERVICE_NAME = ADAPTER_SERVICE_NAME + config.getAdapterService();
                CUSTOMER_INTERF_NAME = CUSTOMER_INTERF_NAME + config.getCustomerInterfService();
				ASSET_SERVICE_NAME = ASSET_SERVICE_NAME + config.getAssetService();
            }
            else {
                ADAPTER_SERVICE_NAME = config.getAdapterService();
                CUSTOMER_INTERF_NAME = config.getCustomerInterfService();
				ASSET_SERVICE_NAME = config.getAssetService();
            }
        }
        
        @ConfigurationProperties(prefix = "application.micorservice.contextPath")
        class ContextPathConfiguration {

            private boolean single;

            private String adapterService;
            
            private String customerInterfService;

            private String assetService;
            /**
             * 获取 single
             * @return single.
             */
            public boolean isSingle() {
                return single;
            }

            
            /**
             * 设置 single
             * @param single single
             */
            public void setSingle(boolean single) {
                this.single = single;
            }

            
            /**
             * 获取 adapterService
             * @return adapterService.
             */
            public String getAdapterService() {
                return adapterService;
            }

            
            /**
             * 设置 adapterService
             * @param adapterService adapterService
             */
            public void setAdapterService(String adapterService) {
                this.adapterService = adapterService;
            }


            /**
             * 获取 customerInterfService
             * @return customerInterfService.
             */
            public String getCustomerInterfService() {
                return customerInterfService;
            }


            /**
             * 设置 customerInterfService
             * @param customerInterfService customerInterfService
             */
            public void setCustomerInterfService(String customerInterfService) {
                this.customerInterfService = customerInterfService;
            }
            
            public String getAssetService() {
				return assetService;
			}


			public void setAssetService(String assetService) {
				this.assetService = assetService;
			}

        }
    }
    
}
