package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jixu.common.type.ResultContainer;
import com.jixu.job.obj.WorkFlowStepStatus;
import com.jixu.mvc.control.model.WfFailedData;
import com.orm.obj.JobsHis;

/** 
 * @ClassName 		WorkFlowService
 * @author  		Pan Wei 
 * @Date 			2016年9月18日 上午10:32:00 	
 * @Description		[]  
 */


public interface WorkFlowService {
	
	public HashMap<String, Object> fetchCurWorkFlow(String[] portfolios, Date ledgerDate, int userid, ResultContainer container);
	
	/**
	 * 获取投资组合当前的做账时间
	 * @param portfolios
	 * @param container
	 * @return
	 */
	public Date fetchNextLedgerDate(String[] portfolios, ResultContainer container);
	
	public void updateWorkFlowStep(int step, List<String> portList, Date ledgerDate, int status);
	
	public List<WfFailedData> loadErrorMsgLog(String[] ports, int category, Date ledgerDate);
	
	public void ignoreErrorMsg(int logid, boolean ignoreStatus);
	
	public List<JobsHis> loadErrorJobsHis(String[] ports, Date ledgrDate, int workFlowStep);
	
	//执行/撤销全部时  先查询当前job的状态
	public List<Integer> loadWorkFlowJobStatus(String[] portfolios, Date ledgerDate,int status);
	
	/**
	 * 获取ledger之后已经完成的job
	 * @param portfolios
	 * @param ledger
	 * @return
	 */
	public List<WorkFlowStepStatus> loadFinishWorkFlowJob(String[] portfolios, Date ledgerDate);
}
