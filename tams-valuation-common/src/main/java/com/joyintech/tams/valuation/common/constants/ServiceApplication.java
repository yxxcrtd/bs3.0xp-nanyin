
/*
 * 文件名：ServiceApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 微服务访问路径定义
 * 
 * @author 何阳阳
 * @version 2.0 2018年9月8日
 * @see ServiceApplication
 * @since 1.0
 */
@Configuration("valuationServiceApplication")
public class ServiceApplication {

    /**
     * 共通服务
     */
    public static String VALUATION_SERVICE_NAME = "http://valuationService";
    
    /**
     * 自定义接口服务
     */
    public static String CUSTOMER_INTERF_NAME = "http://customerInterfService";

	/**
	 * 自定义接口服务
	 */
	public static String VALUATION_BASICDATA_SERVICE_NAME = "http://valuationBasicdataService";

	@Configuration
	@ConditionalOnProperty(prefix = "application.micorservice.contextPath", name = "valuationService")
	@EnableConfigurationProperties(ContextPathConfiguration.class)
	class AutoConfig {

		@Autowired
		private ContextPathConfiguration config;

		@PostConstruct
		private void setConfig() {
			if(!config.isSingle()) {
				VALUATION_SERVICE_NAME = VALUATION_SERVICE_NAME + config.getValuationService();
				CUSTOMER_INTERF_NAME = CUSTOMER_INTERF_NAME + config.getCustomerInterfService();
				VALUATION_BASICDATA_SERVICE_NAME = VALUATION_BASICDATA_SERVICE_NAME + config.getValuationBasicdataService();
			}
			else {
				VALUATION_SERVICE_NAME = config.getValuationService();
				CUSTOMER_INTERF_NAME = config.getCustomerInterfService();
				VALUATION_BASICDATA_SERVICE_NAME = config.getValuationBasicdataService();
			}
		}
	}



	@ConfigurationProperties(prefix = "application.micorservice.contextPath")
    class ContextPathConfiguration {

        private boolean single;

		private String valuationService;

		private String customerInterfService;

		private String valuationBasicdataService;

        public String getCustomerInterfService() {
            return customerInterfService;
        }

        public void setCustomerInterfService(String customerInterfService) {
            this.customerInterfService = customerInterfService;
        }
        
		public boolean isSingle() {
			return single;
		}

		public void setSingle(boolean single) {
			this.single = single;
		}

		public String getValuationService() {
			return valuationService;
		}

		public void setValuationService(String valuationService) {
			this.valuationService = valuationService;
		}

		public String getValuationBasicdataService() {
			return valuationBasicdataService;
		}

		public void setValuationBasicdataService(String valuationBasicdataService) {
			this.valuationBasicdataService = valuationBasicdataService;
		}
	}

}
