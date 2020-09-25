package com.jixu.job.service.Impl;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.jixu.common.type.ResultContainer;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;

/** 
 * @ClassName 		JobTaskControl
 * @author  		Pan Wei 
 * @Date 			2018年4月10日 上午10:29:04 	
 * @Description		[]  
 */

public class JobTaskControl implements Callable<ResultContainer>{
	private JNLJob jniJob;

	private JobParam jobParam;
	
	private RequestAttributes context;
	
	public JNLJob getJniJob() {
		return jniJob;
	}

	public void setJniJob(JNLJob jniJob) {
		this.jniJob = jniJob;
	}
	
	public JobParam getJobParam() {
		return jobParam;
	}

	public void setJobParam(JobParam jobParam) {
		this.jobParam = jobParam;
	}

	public JobTaskControl(JNLJob jniJob, JobParam jobParam, RequestAttributes context) {
		this.jniJob=jniJob;
		this.jobParam=jobParam;
		this.context=context;
	}
	
	
	@Override
	public ResultContainer call() throws Exception {
		if (context != null) {
            RequestContextHolder.setRequestAttributes(context);
        }
		
		ResultContainer container=jniJob.execute(jobParam);
		container.setEntityID(jobParam.getPortfolio());
		return container;
	}
}
