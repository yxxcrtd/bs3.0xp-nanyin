/*
 * 文件名：reportJob.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.dto;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.batch.disclosure.informationdisclourse.quartz.thread.JobDoThread;
import com.joyintech.framework.util.SpringContextUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月5日
 * @see ReportJob
 * @since 1.0
 */
public class ReportJob implements Job {
    
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private JobDoThread jobTodo;
    
    private void init(){
        if(null==jobTodo){
            jobTodo = SpringContextUtil.getBean(JobDoThread.class);
        }
    }

    @Override
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        init();
        String jobId =  (String)context.getMergedJobDataMap().get("JobId");
        jobTodo.pushJob(jobId);
    }

   

}

