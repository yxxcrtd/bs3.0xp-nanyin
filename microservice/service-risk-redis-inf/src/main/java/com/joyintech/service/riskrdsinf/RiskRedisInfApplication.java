/*
 * 文件名：RiskRedisInfApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.MybatisConfiguration;
import com.joyintech.framework.config.WebApplication;

/**
 * 资产服务启动类
 * 
 * @author 何阳阳
 * @version 2.0 2018年9月5日
 * @see RiskRedisInfApplication
 * @since 1.0
 */
@ComponentScan(basePackages = "com.joyintech", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
    RestController.class}))
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}, excludeName = {
    "org.activiti.spring.boot.SecurityAutoConfiguration",
    "org.activiti.spring.boot.JpaProcessEngineAutoConfiguration"})
@EnableEurekaClient
@SpringBootApplication(exclude = {MybatisConfiguration.class})
public class RiskRedisInfApplication extends WebApplication {

    /**
     * 启动
     * 
     * @param args 参数
     * @see
     * @since 1.0
     */
    public static void main(String[] args) {
        JoyinApplication.run(RiskRedisInfApplication.class, args);
    }

}
