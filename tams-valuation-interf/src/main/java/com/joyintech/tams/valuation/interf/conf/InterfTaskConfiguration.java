/*
 * 文件名：InterfTaskConfiguration.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.conf;

import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 上游接口定时任务配置 
 * 
 * @author 王波
 * @version 1.0 2018年6月7日
 * @see InterfTaskConfiguration
 * @since 1.0
 */
@Configuration
@ImportResource("classpath:interf-task.xml")
public class InterfTaskConfiguration {
    
    @Bean(value = "jobRepository")
    public JobRepository initRepository(MapJobRepositoryFactoryBean mapJobFactoryBean)
        throws Exception {
        return mapJobFactoryBean.getObject();
    }
    
    @Bean(value = "jobBuilderFactory")
    public JobBuilderFactory initJobBuilderFactory(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository);
    }
    
    @Bean(value = "stepBuilderFactory")
    public StepBuilderFactory initStepBuilderFactory(JobRepository jobRepository,
                                                     DataSourceTransactionManager transactionManager) {
        return new StepBuilderFactory(jobRepository, transactionManager);
    }

    @Bean(value = "taskExecutor")
    public ThreadPoolTaskExecutor initThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(30);
        return taskExecutor;
    }

    @Bean(value = "jobRegistry")
    public ListableJobLocator initListableJobLocator() {
        return new MapJobRegistry();
    }

    @Bean(value = "jobLauncher")
    public SimpleJobLauncher initJobLauncher(JobRepository jobRepository,
                                             ThreadPoolTaskExecutor taskExecutor) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
       // jobLauncher.setTaskExecutor(taskExecutor);
        return jobLauncher;
    }   
    

    @Bean(value = "mapJobFactoryBean")
    public MapJobRepositoryFactoryBean initMapJobRepositoryFactoryBean()
        throws Exception {
        return new MapJobRepositoryFactoryBean();
    }
}
