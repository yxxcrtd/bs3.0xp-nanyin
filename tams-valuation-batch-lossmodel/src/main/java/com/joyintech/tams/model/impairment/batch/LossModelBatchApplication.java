/*
 * 文件名：ValuationBatchApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.model.impairment.batch;

import com.joyintech.framework.config.Application;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.joyintech.framework.JoyinApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

/**
 * 批处理启动类
 * @author zznn
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}, excludeName = {
		"org.activiti.spring.boot.SecurityAutoConfiguration",
		"org.activiti.spring.boot.JpaProcessEngineAutoConfiguration"})
@ComponentScan(basePackages = "com.joyintech", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
		RestController.class}))
@SpringBootApplication
public class LossModelBatchApplication extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    JoyinApplication.run(LossModelBatchApplication.class, args);
	}

}
