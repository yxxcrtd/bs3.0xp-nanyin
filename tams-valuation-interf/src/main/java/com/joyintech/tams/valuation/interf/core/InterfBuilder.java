/*
 * 文件名：InterfBuilder.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.interf.core.entity.JoyinInterf;
import com.joyintech.tams.valuation.interf.core.exception.InterfException;

@Component
@Scope("prototype")
public class InterfBuilder<R extends InterfBaseReader<? extends InterfNeedOperateEntity>, P extends InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, W extends InterfBaseWriter<BaseEntity>>  {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private List<String> stepNames;
    
    @Autowired
    private InterfProcessorListener processorListener;
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private InterfWriterListener writerListener;
    
    @Autowired
    private InterfReaderListener readerListener;

    @Value("${interf.properties.chunkNumber}")
    private int chunkNumber;

    @Value("${interf.properties.concurrency}")
    private int concurrency;
    
    /**
     * step数组
     */
    private List<JoyinInterf> joyinInterfList;


    private Job job;

    private JobParameters parameters;
    
    private InterfBaseService baseInterfService;
    

    /**
     * 是否已初始化
     */
    private Boolean isInited;
    
    public InterfBuilder<R, P, W> init(InterfBaseService baseInterfService) {
        isInited = true;
        joyinInterfList = new ArrayList<>();
        stepNames = new ArrayList<>();
        this.baseInterfService = baseInterfService;
        processorListener.setInterfBaseService(baseInterfService);
        writerListener.setService(baseInterfService);
        readerListener.setService(baseInterfService);
        return this;
    }
    
    /**
     * 批处理的step构建方法，每次调用构建一个step，stepname必须唯一
     *
     * @param stepName step名称
     * @param reader Reader
     * @param processor Processor
     * @param writer Writer
     * @return BatchBuilder
     */
    public InterfBuilder<R, P, W> batch(String stepName, R reader, P processor, W writer)
        throws InterfException {
        if (isInited == null || !isInited) {
            throw new InterfException("请先调用init方法。");
        }
        if (stepNames.contains(stepName)) {
            throw new InterfException("step名称必须唯一。");
        }
        // 每次启动批处理是，初始化reader，确保能进createList方法
        ((InterfBaseReader<?>)reader).init();
        stepNames.add(stepName);
        
        /**
         * modify jinamdong 20180819
         * reader\processer\writer继承runner的参数
         */
        reader.setParams(baseInterfService.getParams());
        processor.setParams(baseInterfService.getParams());
        writer.setParams(baseInterfService.getParams());
        
        JoyinInterf batch = new JoyinInterf(stepName, reader, processor, writer);
        joyinInterfList.add(batch);
        return this;
    }
    
    public InterfBuilder<R, P, W> builder(String jobName)
        throws InterfException {
        if (StringUtil.isEmpty(jobName)) {
            throw new InterfException("job名称不能为空");
        }
        List<Step> steps = new ArrayList<>();

        if (joyinInterfList != null && !joyinInterfList.isEmpty()) {
            for (JoyinInterf batch : joyinInterfList) {
                Step step = stepBuilderFactory.get(
                    batch.getStepName()).<InterfNeedOperateEntity, BaseEntity> chunk(
                        chunkNumber).faultTolerant().skipPolicy(new JoyinSkipPolicy()).retryLimit(
                            0).reader(batch.getReader()).listener(readerListener).processor(batch.getProcessor()).listener(
                                processorListener).writer(batch.getWriter()).listener(
                                    writerListener).taskExecutor(new SimpleAsyncTaskExecutor()) // 设置并发方式执行
                    .throttleLimit(concurrency).build();
                steps.add(step);
            }
            JobFlowBuilder jobBuilder = jobBuilderFactory.get(jobName).incrementer(
                new RunIdIncrementer()).flow(steps.subList(0, 1).get(0));
            steps.subList(0, 1).clear();

            if (!steps.isEmpty()) {
                for (Step step : steps) {
                    jobBuilder.next(step);
                }
            }
            job = jobBuilder.end().build();
            parameters = new JobParametersBuilder().addDate("date",
                new Date()).addString("randomString",
                    String.valueOf(
                        Math.random() + DateTime.now().getMillis())).toJobParameters();
        }
        close();
        return this;
    }

    public JobExecution run() {
        JobExecution result = null;
        try {
            result = jobLauncher.run(job, parameters);
        }
        catch (JobExecutionAlreadyRunningException | JobRestartException
            | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            logger.error("", e);
        }
        return result;
    }

    private void close() {
        isInited = null;
        joyinInterfList = null;
        this.baseInterfService = null;
    }
}
