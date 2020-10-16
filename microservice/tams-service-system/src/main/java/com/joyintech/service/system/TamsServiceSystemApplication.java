package com.joyintech.service.system;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.Application;

/**
 * 微服务系统容器启动入口
 * @author Zim
 * @date 2020-09-30
 */
@ComponentScan(basePackages = "com.joyintech", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class }))
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class }, 
                         excludeName = {"org.activiti.spring.boot.SecurityAutoConfiguration",
		                                "org.activiti.spring.boot.JpaProcessEngineAutoConfiguration"}
                        )
@EnableEurekaClient
@SpringBootApplication
public class TamsServiceSystemApplication extends Application {
	
	public static void main(String[] args) {
        JoyinApplication.run(TamsServiceSystemApplication.class, args);
    }
	
}
