package com.joyintech.tams.exchange.quoteterminal;

import com.joyintech.framework.config.RestTemplateAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(basePackages = {"com.joyintech.framework.config","com.joyintech.redis","com.joyintech.tams.exchange.common.cache", "com.joyintech.tams.exchange.quoteterminal"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {RestTemplateAutoConfiguration.class})})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, excludeName = {
        "org.activiti.spring.boot.SecurityAutoConfiguration",
        "org.activiti.spring.boot.JpaProcessEngineAutoConfiguration"})
@EnableScheduling
public class QuoteTerminalApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteTerminalApplication.class, args);
    }


}
