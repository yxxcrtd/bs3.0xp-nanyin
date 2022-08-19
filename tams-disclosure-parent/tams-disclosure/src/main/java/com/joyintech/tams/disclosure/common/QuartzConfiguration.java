package com.joyintech.tams.disclosure.common;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

    //定时器每隔1分钟执行一次
    private String repInfo_CronExpression = "0 0/1 * * * ? *";

    //定时更新模板状态
    @Bean(name="repInfo_JobDetail")
    public MethodInvokingJobDetailFactoryBean repInfo_JobDetail(RepInfoUpdate repInfoUpdate) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(repInfoUpdate);
        // 需要执行的方法
        jobDetail.setTargetMethod("updateRepInfo");
        return jobDetail;
    }

    // 配置触发器1
    @Bean(name = "repInfo_Trigger")
    public CronTriggerFactoryBean  repInfo_Trigger(JobDetail repInfo_JobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
        trigger.setJobDetail(repInfo_JobDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        trigger.setCronExpression(repInfo_CronExpression);
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
