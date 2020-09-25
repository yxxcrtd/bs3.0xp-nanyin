/*
 * 文件名：QuartzConfiguration.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/5/27 15:09
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.sale.config;

import com.joyintech.interf.bankname.sale.job.SaleInterfJob;

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
 * @author 吴鼎
 * @version 1.0 2019年07月05日
 * @see
 * @since 1.0
 */
@Configuration
@PropertySource("classpath:quartz.properties")
public class QuartzSaleConfiguration {

    //认申购接口
    @Value("${saleAlloCronExpression}")
    private String saleAlloCronExpression;
    
    //赎回到期接口
    @Value("${saleRedeemCronExpression}")
    private String saleRedeemCronExpression;
    
    //份额强增强减接口
    @Value("${saleReduceCronExpression}")
    private String saleReduceCronExpression;
    
    //分红汇总接口
    @Value("${saleBonusColletCronExpression}")
    private String saleBonusColletCronExpression;
    
    //产品计提接口
    @Value("${saleProdAccrueCronExpression}")
    private String saleProdAccrueCronExpression;
    
    //行情下发接口
    @Value("${saleMarketIssueCronExpression}")
    private String saleMarketIssueCronExpression;
    
    //产品要素下发接口
    @Value("${saleBaseInfoCronExpression}")
    private String saleBaseInfoCronExpression;
    
    //产品要素下发接口
    @Value("${saleBonusPlanCronExpression}")
    private String saleBonusPlanCronExpression;

    @Bean(name = "saleAlloJobDetail")
    public MethodInvokingJobDetailFactoryBean saleAlloJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("receiveAlloFromSale");
        return jobDetail;
    }

    // 配置触发器 ******* 认申购接口
//    @Bean(name = "saleAlloTrigger")
//    public CronTriggerFactoryBean  saleAlloTrigger(JobDetail saleAlloJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleAlloJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleAlloCronExpression);
//        return trigger;
//    }
    
    @Bean(name = "saleRedeemJobDetail")
    public MethodInvokingJobDetailFactoryBean saleRedeemJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("receiveRedeemFromSale");
        return jobDetail;
    }

    // 配置触发器 ******* 赎回到期接口
//    @Bean(name = "saleRedeemTrigger")
//    public CronTriggerFactoryBean  saleRedeemTrigger(JobDetail saleRedeemJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleRedeemJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleRedeemCronExpression);
//        return trigger;
//    }
    
    @Bean(name = "saleReduceJobDetail")
    public MethodInvokingJobDetailFactoryBean saleReduceJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("receiveAddReduceSale");
        return jobDetail;
    }

    // 配置触发器 ******* 份额强增强减接口
//    @Bean(name = "saleReduceTrigger")
//    public CronTriggerFactoryBean  saleReduceTrigger(JobDetail saleReduceJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleReduceJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleReduceCronExpression);
//        return trigger;
//    }
    
    @Bean(name = "saleBonusColletJobDetail")
    public MethodInvokingJobDetailFactoryBean saleBonusColletJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("receiveBonusCollectSale");
        return jobDetail;
    }

    // 配置触发器 ******* 分红汇总接口
//    @Bean(name = "saleBonusColletTrigger")
//    public CronTriggerFactoryBean  saleBonusColletTrigger(JobDetail saleBonusColletJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleBonusColletJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleBonusColletCronExpression);
//        return trigger;
//    }
    
    @Bean(name = "saleProdAccrueJobDetail")
    public MethodInvokingJobDetailFactoryBean saleProdAccrueJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("receiveAddReduceSale");
        return jobDetail;
    }

    // 配置触发器 ******* 产品计提接口
//    @Bean(name = "saleProdAccrueTrigger")
//    public CronTriggerFactoryBean  saleProdAccrueTrigger(JobDetail saleProdAccrueJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleProdAccrueJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleProdAccrueCronExpression);
//        return trigger;
//    }
    
    @Bean(name = "saleMarketIssueJobDetail")
    public MethodInvokingJobDetailFactoryBean saleMarketIssueJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("sendMarketIssueToSale");
        return jobDetail;
    }

    // 配置触发器 ******* 行情下发接口
//    @Bean(name = "saleMarketIssueTrigger")
//    public CronTriggerFactoryBean  saleMarketIssueTrigger(JobDetail saleMarketIssueJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleMarketIssueJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleMarketIssueCronExpression);
//        return trigger;
//    }
    
    @Bean(name = "saleBaseInfoJobDetail")
    public MethodInvokingJobDetailFactoryBean saleBaseInfoJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("sendBaseInfoToSale");
        return jobDetail;
    }

    // 配置触发器 ******* 产品要素下发接口
    @Bean(name = "saleBaseInfoTrigger")
    public CronTriggerFactoryBean  saleBaseInfoTrigger(JobDetail saleBaseInfoJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
        trigger.setJobDetail(saleBaseInfoJobDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        trigger.setCronExpression(saleBaseInfoCronExpression);
        return trigger;
    }
    
    @Bean(name = "saleBonusPlanJobDetail")
    public MethodInvokingJobDetailFactoryBean saleBonusPlanJobDetail(SaleInterfJob saleInterfJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(saleInterfJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("sendBonusPlanToSale");
        return jobDetail;
    }

    // 配置触发器 ******* 分红方案下发接口
//    @Bean(name = "saleBonusPlanTrigger")
//    public CronTriggerFactoryBean  saleBonusPlanTrigger(JobDetail saleBonusPlanJobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
//        trigger.setJobDetail(saleBonusPlanJobDetail);
//        // 设置任务启动延迟
//        trigger.setStartDelay(0);
//        trigger.setCronExpression(saleBonusPlanCronExpression);
//        return trigger;
//    }

    
    
    
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
