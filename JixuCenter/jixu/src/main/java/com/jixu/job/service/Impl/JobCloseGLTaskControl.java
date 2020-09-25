package com.jixu.job.service.Impl;

import java.util.Date;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.jixu.common.type.ResultContainer;
import com.jixu.job.service.CloseControlService;

/** 
 * @ClassName 		JobTaskControl
 * @author  		Pan Wei 
 * @Date 			2018年4月10日 上午10:29:04 	
 * @Description		[]  
 */

public class JobCloseGLTaskControl implements Callable<ResultContainer>{
	
	private CloseControlService closeService;

	private int userID;
	
	private String portnum;
	
	private Date closeDate;
	
	private RequestAttributes context;

	public JobCloseGLTaskControl(CloseControlService closeService, int userID, String portnum, Date closeDate, RequestAttributes context) {
		this.closeService=closeService;
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
		ResultContainer container=closeService.closeGL(userID, portnum, closeDate);
		container.setEntityID(portnum);
		return container;
	}


	public String getPortnum() {
		return portnum;
	}


	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
	
}
