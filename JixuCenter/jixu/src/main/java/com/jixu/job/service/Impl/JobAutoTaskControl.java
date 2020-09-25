package com.jixu.job.service.Impl;

import java.util.Date;
import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.jixu.common.type.ResultContainer;
import com.jixu.job.service.AutoJobService;

/** 
 * @ClassName 		JobTaskControl
 * @author  		Pan Wei 
 * @Date 			2018年4月10日 上午10:29:04 	
 * @Description		[]  
 */

public class JobAutoTaskControl implements Callable<ResultContainer>{
	
	private AutoJobService autoJobService;
	
	private RequestAttributes context;

	private int userID;
	
	private String portnum;
	
	private Date closeDate;

	public JobAutoTaskControl(AutoJobService autoJobService, int userID, String portnum, Date closeDate, RequestAttributes context) {
		this.autoJobService=autoJobService;
		this.userID=userID;
		this.portnum=portnum;
		this.closeDate=closeDate;
		this.context=context;
	}
	
	
	@Override
	public ResultContainer call() throws Exception {
		if (context != null) {
            RequestContextHolder.setRequestAttributes(context);
        }
		
		ResultContainer container=autoJobService.executeAutoJob(userID, portnum, closeDate);
		container.setEntityID(portnum);
		return container;
	}
}
