package com.jixustart;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.config.JasyptConfiguration;

@Import({JasyptConfiguration.class})
@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableEurekaClient
@EnableCaching
@EnableAsync
@ComponentScan(basePackages ="com.joyintech.jixu", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
    RestController.class}))
public class AdapterApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(AdapterApplication.class, args);
	}
}
