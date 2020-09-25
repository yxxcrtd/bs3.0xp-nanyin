package com.jixu.adapter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

import com.jixu.adapter.service.export.ExpAssembleOutput;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.ResultContainer;

public class ExecuteReturnJobTask  implements Callable<ResultContainer>{
	private int userid;
	
	private int sourceid;
	
	private Date asOfDate;
	
	private DataServiceCenter interafaceDataCenter;
	
	private ExpAssembleOutput expAssembleOutput;
	
	private InterfaceSource  source;


	public ExecuteReturnJobTask(DataServiceCenter interafaceDataCenter, ExpAssembleOutput expAssembleOutput,int userid, InterfaceSource source, Date asOfDate) {
		this.userid=userid;
		this.sourceid=source.getSourceID();
		this.asOfDate=asOfDate;
		this.interafaceDataCenter=interafaceDataCenter;
		this.expAssembleOutput=expAssembleOutput;
		this.source=source;
	}
	
	
	@Override
	public ResultContainer call() throws Exception {
		if(source.getSourceType()!=InterfaceSourceType.exportType) {
			return interafaceDataCenter.importData(userid, sourceid, asOfDate, 0);
		}else {
			String[] params = null;
			return expAssembleOutput.executeExport(sourceid, params);
		}
		
	}
}
