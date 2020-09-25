package com.jixu.job.service.Impl;

import java.util.Date;
import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.service.CalNavService;

/** 
 * @ClassName 		JobTaskControl
 * @author  		Pan Wei 
 * @Date 			2018年4月10日 上午10:29:04 	
 * @Description		[]  
 */

public class JobValReportTaskControl implements Callable<ResultContainer>{
	
	private CalNavService calNavService;

	private String portnum;
	
	private Date closeDate;
	
	private RequestAttributes context;

	public JobValReportTaskControl(CalNavService calNavService, String portnum, Date closeDate, RequestAttributes context) {
		this.calNavService=calNavService;
		this.portnum=portnum;
		this.closeDate=closeDate;
		this.context=context;
	}
	
	
	@Override
	public ResultContainer call() throws Exception {
		if (context != null) {
            RequestContextHolder.setRequestAttributes(context);
        }
		calNavService.genValuationReport(portnum, closeDate);
		return null;
	}
}
