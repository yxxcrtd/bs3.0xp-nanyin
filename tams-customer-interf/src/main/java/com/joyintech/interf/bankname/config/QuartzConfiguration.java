/*
 * 文件名：QuartzConfiguration.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/5/27 15:09
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.config;

import com.joyintech.interf.bankname.job.RdiJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author 王波
 * @version 1.0 2019年05月27日
 * @see
 * @since 1.0
 */
@Configuration
@PropertySource("classpath:quartz.properties")
public class QuartzConfiguration {

    @Value("${saleImixCounterXmlDealerExpression}")
    private String saleImixCounterXmlDealerExpression;
    @Bean(name = "rdiJobDetail")
    public MethodInvokingJobDetailFactoryBean demoJobDetail(RdiJob rdiJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(rdiJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("analysisRdi");
        return jobDetail;
    }

    // 配置触发器1
    @Bean(name = "demoTrigger")
    public CronTriggerFactoryBean  demoTrigger(JobDetail rdiJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
        trigger.setJobDetail(rdiJobDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        trigger.setCronExpression(saleImixCounterXmlDealerExpression);
        return trigger;
    }

    // 配置Scheduler
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger [] triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(triggers);
        return bean;
    }
}
