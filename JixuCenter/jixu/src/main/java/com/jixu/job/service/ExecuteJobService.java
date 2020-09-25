package com.jixu.job.service;

import java.util.Date;

import com.jixu.common.type.ResultContainer;

/** 
 * @ClassName 		ExecuteJobService
 * @author  		Pan Wei 
 * @Date 			2016年10月11日 下午10:34:06 	
 * @Description		[]  
 */

public interface ExecuteJobService {

	public ResultContainer executeDailyJob(int userID, String[] portnums, Date closeDate);
	
	public ResultContainer executeJob(int userID, String[] portnums, Date closeDate, int jobType);
	
	public ResultContainer removeJob(int userID, String[] portnums, Date closeDate);
	
	public ResultContainer removeJob(int userID, String[] portnums, Date closeDate, int jobType);
	
	public ResultContainer executeJob(int userID, String portnum, Date ledgerDate, int jobHisID);
	
	public ResultContainer reExecuteJob(int userID, String portnum, Date ledgerDate, int jobHisID);
	
	public ResultContainer executeAutoJob(int userID, String[] portnum, Date ledgerDate);
	
	public ResultContainer executeCloseTranJob(int userID, String[] portnums, Date closeDate);
	
	public ResultContainer executeCreateNavJob(int userID, String[] portnums, Date closeDate);
	
	public ResultContainer executeCloseGLJob(int userID, String[] portnums, Date closeDate);

	public ResultContainer returnAutoJobFalseMsg(ResultContainer mainContainer);

	public void genValuationReports(String[] portnums, Date closeDate);
	
	/**
	 * 运行指定的job
	 * @param portnums
	 * @param closeDate
	 * @param jobIDs
	 * @return
	 */
	public ResultContainer executeAppointedDailyJob(String[] portnums, Date closeDate, int[] jobIDs);
	
	/**
	 * 运行指定job
	 * @param portnum
	 * @param ledgerDate
	 * @param jobnum
	 * @return
	 */
	public ResultContainer executeJobByJobID(String portnum, Date ledgerDate, int jobnum);
}
