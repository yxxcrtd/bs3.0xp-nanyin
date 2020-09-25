/*
 * 文件名：InvestApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年1月13日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.invest.interf;

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
 * 
 * 投资交易系统接口服务启动类
 * 
 * @author 王波
 * @version 1.0 2020年1月13日
 * @see InvestInterfApplication
 * @since 1.0
 */
@ComponentScan(basePackages = "com.joyintech", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
		RestController.class }))
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class }, excludeName = {
		"org.activiti.spring.boot.SecurityAutoConfiguration",
		"org.activiti.spring.boot.JpaProcessEngineAutoConfiguration" })
@EnableEurekaClient
@SpringBootApplication
public class InvestInterfApplication extends Application {


	public static void main(String[] args) {
		JoyinApplication.run(InvestInterfApplication.class, args);
	}

}
